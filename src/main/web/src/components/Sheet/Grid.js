import React, { Component, PropTypes } from 'react'

import * as ActionType from '../../constants/ActionTypes'
import * as MessageType from '../../constants/MessageTypes'

import Utils from '../../util/Utils'
import Column from './Column'
import Cell from './Cell'
import Row from './Row'

export default class Grid extends Component
{
    constructor(props)
    {
        super(props);

        const { maxColumn, maxRow } = this.props;

        this.mouseStartCoords = [0, 0];
        this.mouseStartElement = null;
        this.startingColumnWidth = 0;
        this.resizingColumn = false;
        this.startingRowHeight = 0;
        this.resizingRow = false;
        this.mouseDown = false;

        this.state = {
            columnSizes: [],
            rowSizes: [],
            scrollLeft: 0,
            scrollTop: 0,
            windowHeight: window.innerHeight,
            windowWidth: window.innerWidth
        };

        // Calculate required rows and columns based on window size.
        let minRow = (this.state.windowHeight / 23) - 4;
        let minColumn = (this.state.windowWidth / 100);

        let rows = maxRow > minRow ? maxRow : minRow;
        let columns = maxColumn > minColumn ? maxColumn : minColumn;

        for (let i = 0; i < columns; ++i)
            this.state.columnSizes.push(100);

        for (let i = 0; i < rows; ++i)
            this.state.rowSizes.push(24);
    }

    render()
    {
        const { id, cells, maxRow, maxColumn, selectedCells, editingCell } = this.props;

        // Calculate required rows and columns based on window size.
        let minRow = (this.state.windowHeight / 23) - 4;
        let minColumn = (this.state.windowWidth / 100);

        let rows = maxRow > minRow ? maxRow : minRow;
        let columns = maxColumn > minColumn ? maxColumn : minColumn;

        let head = [];
        let body = [];

        // Add the first empty corner column.
        head.push(<Column key={-1} empty={true} size={30} left={this.state.scrollLeft} scroll={this.state.scrollTop} />);

        let columnLeft = 30;

        // Populate columns.
        for (let x = 0; x < columns; ++x)
        {
            // Check if this column is selected.
            let selected = false;

            for (let selection of selectedCells)
            {
                if (selection[0] != x)
                    continue;

                selected = true;
                break;
            }

            head.push(<Column key={x} x={x} selected={selected} scroll={this.state.scrollTop} left={columnLeft} size={this.state.columnSizes[x]} />);
            columnLeft += this.state.columnSizes[x];
        }

        // Populate rows.
        for (let y = 0; y < rows; ++y)
        {
            let rowCells = [];

            // Check if this row is selected.
            let selectedRow = false;

            for (let selection of selectedCells)
            {
                if (selection[1] != y)
                    continue;

                selectedRow = true;
                break;
            }

            // Populate cells.
            for (let x = 0; x < columns; ++x)
            {
                // Check if this cell is selected.
                let selected = false;

                for (let selection of selectedCells)
                {
                    if (selection[0] != x || selection[1] != y)
                        continue;

                    selected = true;
                    break;
                }

                let editing = editingCell !== null && editingCell[0] == x && editingCell[1] == y;

                rowCells.push(<Cell
                    sheet={id}
                    key={x + 'x' + y}
                    cell={cells[x + 'x' + y]}
                    x={x}
                    y={y}
                    selected={selected}
                    editing={editing}
                    size={this.state.columnSizes[x]}
                    height={this.state.rowSizes[y]} />
                );
            }

            body.push(<Row key={y} y={y} selected={selectedRow} scroll={this.state.scrollLeft} size={this.state.rowSizes[y]}>{rowCells}</Row>);
        }

        return (
            <div className="grid-container">
                <div
                    ref="grid"
                    className="sheet-grid"
                    onMouseDown={(e) => this.onMouseDown(e)}
                    onMouseMove={(e) => this.onMouseMove(e)}
                    onMouseUp={(e) => this.onMouseUp(e)}>
                    <div className="row empty-row" key={-1}>
                        {head}
                    </div>
                    {body}
                </div>
            </div>
        );
    }

    componentDidMount()
    {
        this.refs.grid.addEventListener('scroll', (e) => this.onScroll(e));
        window.addEventListener('resize', () => this.onResize());
    }

    componentWillUnmount()
    {
        this.refs.grid.removeEventListener('scroll', (e) => this.onScroll(e));
        window.removeEventListener('resize', () => this.onResize());
    }

    onScroll(event)
    {
        this.setState({
            scrollLeft: this.refs.grid.scrollLeft,
            scrollTop: this.refs.grid.scrollTop
        });
    }

    onResize()
    {
        const { maxColumn, maxRow } = this.props;

        let rowSizes = this.state.rowSizes;
        let columnSizes = this.state.columnSizes;

        let minRow = (window.innerHeight / 23) - 4;
        let minColumn = (window.innerWidth / 100);

        let rows = maxRow > minRow ? maxRow : minRow;
        let columns = maxColumn > minColumn ? maxColumn : minColumn;

        if (rows > rowSizes.length)
        {
            for (let i = 0; i < rows - rowSizes.length; ++i)
                rowSizes.push(24);
        }
        else if (rows < rowSizes.length)
        {
            rowSizes = rowSizes.slice(0, rows);
        }

        if (columns > columnSizes.length)
        {
            for (let i = 0; i < columns - columnSizes.length; ++i)
                columnSizes.push(100);
        }
        else if (columns < columnSizes.length)
        {
            columnSizes = columnSizes.slice(0, columns);
        }

        this.setState({
            windowHeight: window.innerHeight,
            windowWidth: window.innerWidth,
            rowSizes: rowSizes,
            columnSizes: columnSizes
        });
    }

    onMouseDown(event)
    {
        // We only care about the left mouse button.
        if (event.button != 0)
            return;

        this.mouseStartCoords = [ event.pageX, event.pageY ];
        this.mouseStartElement = event.target;
        this.mouseDown = true;

        let elementClass = this.mouseStartElement.className;

        // User clicked a column header.
        if (elementClass.indexOf('column') !== -1 && elementClass.indexOf('empty') === -1)
        {
            let columnX = parseInt(this.mouseStartElement.getAttribute('data-x'), 10);

            // Are we trying to resize the column?
            if (this.mouseStartCoords[0] == (this.mouseStartElement.getBoundingClientRect().left + this.mouseStartElement.offsetWidth) - 1)
            {
                this.resizingColumn = true;
                this.startingColumnWidth = this.state.columnSizes[columnX];
                return;
            }

            this.resizingColumn = false;
            this.startingColumnWidth = 0;

            // Select entire column.
            let cells = [];

            for (let i = 0; i < this.state.rowSizes.length; ++i)
                cells.push([ columnX, i ]);

            store.dispatch({
                type: ActionType.SET_SELECTED_CELLS,
                sheet: this.props.id,
                cells: cells
            });
            
            Utils.dispatchMessage(MessageType.SET_SELECTED_CELLS, {
                sheet: this.props.id,
                cells: cells
            });

            return;
        }

        // User clicked a row label.
        if (elementClass.indexOf('row-label') !== -1)
        {
            let rowY = parseInt(this.mouseStartElement.getAttribute('data-y'), 10);

            // Are we trying to resize the row?
            if (this.mouseStartCoords[1] == (this.mouseStartElement.getBoundingClientRect().top + this.mouseStartElement.offsetHeight) - 1)
            {
                this.resizingRow = true;
                this.startingRowHeight = this.state.rowSizes[rowY];
                return;
            }

            this.resizingRow = false;
            this.startingRowHeight = 0;

            // Select entire row.
            let cells = [];

            for (let i = 0; i < this.state.columnSizes.length; ++i)
                cells.push([ i, rowY ]);

            store.dispatch({
                type: ActionType.SET_SELECTED_CELLS,
                sheet: this.props.id,
                cells: cells
            });

            Utils.dispatchMessage(MessageType.SET_SELECTED_CELLS, {
                sheet: this.props.id,
                cells: cells
            });

            return;
        }

        // User clicked a cell.
        if (elementClass.indexOf('cell') !== -1)
        {
            let cellX = parseInt(this.mouseStartElement.getAttribute('data-x'), 10);
            let cellY = parseInt(this.mouseStartElement.getAttribute('data-y'), 10);

            // Select cell.
            store.dispatch({
                type: ActionType.SET_SELECTED_CELLS,
                sheet: this.props.id,
                cells: [
                    [ cellX, cellY ]
                ]
            });

            Utils.dispatchMessage(MessageType.SET_SELECTED_CELLS, {
                sheet: this.props.id,
                cells: [
                    [ cellX, cellY ]
                ]
            });

            return;
        }
    }

    onMouseMove(event)
    {
        if (!this.mouseDown)
            return;

        // Handle column resizing.
        if (this.resizingColumn)
        {
            let columnX = parseInt(this.mouseStartElement.getAttribute('data-x'), 10);

            let widthDiff = event.pageX - this.mouseStartCoords[0];

            let finalWidth = this.startingColumnWidth + widthDiff;

            if (finalWidth < 100)
                finalWidth = 100;

            this.setState({
                columnSizes: [
                    ...this.state.columnSizes.slice(0, columnX),
                    finalWidth,
                    ...this.state.columnSizes.slice(columnX + 1)
                ]
            });

            return;
        }

        // Handle row resizing.
        if (this.resizingRow)
        {
            let rowY = parseInt(this.mouseStartElement.getAttribute('data-y'), 10);

            let heightDiff = event.pageY - this.mouseStartCoords[1];

            let finalHeight = this.startingRowHeight + heightDiff;

            if (finalHeight < 24)
                finalHeight = 24;

            this.setState({
                rowSizes: [
                    ...this.state.rowSizes.slice(0, rowY),
                    finalHeight,
                    ...this.state.rowSizes.slice(rowY + 1)
                ]
            });

            return;
        }

        // Handle mouse-drag multiple cell selection.
        if (this.mouseStartElement.className.indexOf('cell') !== -1 && event.target.className.indexOf('cell') !== -1)
        {
            let cellX = parseInt(this.mouseStartElement.getAttribute('data-x'), 10);
            let cellY = parseInt(this.mouseStartElement.getAttribute('data-y'), 10);

            let targetX = parseInt(event.target.getAttribute('data-x'), 10);
            let targetY = parseInt(event.target.getAttribute('data-y'), 10);

            let minX = cellX < targetX ? cellX : targetX;
            let maxX = cellX > targetX ? cellX : targetX;

            let minY = cellY < targetY ? cellY : targetY;
            let maxY = cellY > targetY ? cellY : targetY;

            let cells = [];

            for (let x = minX; x <= maxX; ++x)
                for (let y = minY; y <= maxY; ++y)
                    cells.push([ x, y ]);

            store.dispatch({
                type: ActionType.SET_SELECTED_CELLS,
                sheet: this.props.id,
                cells: cells
            });

            Utils.dispatchMessage(MessageType.SET_SELECTED_CELLS, {
                sheet: this.props.id,
                cells: cells
            });

            return;
        }
    }

    onMouseUp(event)
    {
        if (event.button != 0)
            return;

        this.mouseDown = false;
        this.resizingColumn = false;
        this.resizingRow = false;
    }
}
