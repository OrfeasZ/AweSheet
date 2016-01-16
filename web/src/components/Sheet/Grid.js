import React, { Component, PropTypes } from 'react'

import Column from './Column'
import Cell from './Cell'
import Row from './Row'

export default class Grid extends Component
{
    constructor(props)
    {
        super(props);

        const { maxColumn, maxRow } = this.props;

        this.state = {
            columnSizes: [],
            rowSizes: [],
            scrollLeft: 0,
            scrollTop: 0,
            windowHeight: window.innerHeight,
            windowWidth: window.innerWidth
        };

        // Calculate required rows and columns based on window size.
        let minRow = (this.state.windowHeight / 23) - 5;
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
        let minRow = (this.state.windowHeight / 23) - 5;
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

                rowCells.push(<Cell sheet={id} key={x + 'x' + y} cell={cells[x + 'x' + y]} x={x} y={y} selected={selected} editing={editing} size={this.state.columnSizes[x]} />);
            }

            body.push(<Row key={y} y={y} selected={selectedRow} scroll={this.state.scrollLeft} size={this.state.rowSizes[y]}>{rowCells}</Row>);
        }

        return (
            <div className="grid-container">
                <div ref="grid" className="sheet-grid">
                    <div className="row">
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

        let minRow = (window.innerHeight / 23) - 5;
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
}
