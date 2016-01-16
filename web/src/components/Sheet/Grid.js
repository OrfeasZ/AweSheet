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

        // Calculate required rows and columns based on window size.
        let minRow = (window.innerHeight / 23) - 1;
        let minColumn = (window.innerWidth / 70) + 1;

        let rows = maxRow > minRow ? maxRow : minRow;
        let columns = maxColumn > minColumn ? maxColumn : minColumn;

        this.state = {
            columnSizes: [],
            rowSizes: [],
            scrollLeft: 0,
            scrollTop: 0
        };

        for (let i = 0; i < columns; ++i)
            this.state.columnSizes.push(70);

        for (let i = 0; i < rows; ++i)
            this.state.rowSizes.push(24);
    }

    render()
    {
        const { id, cells, maxRow, maxColumn, selectedCells, editingCell } = this.props;

        // Calculate required rows and columns based on window size.
        let minRow = (window.innerHeight / 23) - 1;
        let minColumn = (window.innerWidth / 70) + 1;

        let rows = maxRow > minRow ? maxRow : minRow;
        let columns = maxColumn > minColumn ? maxColumn : minColumn;

        let head = [];
        let body = [];

        head.push(<Column key={-1} empty={true} left={this.state.scrollLeft} scroll={this.state.scrollTop} />);

        let columnLeft = 30;

        for (let x = 0; x < columns; ++x)
        {
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
    }

    componentWillUnmount()
    {
        this.refs.grid.removeEventListener('scroll', (e) => this.onScroll(e));
    }

    onScroll(event)
    {
        this.setState({
            scrollLeft: this.refs.grid.scrollLeft,
            scrollTop: this.refs.grid.scrollTop
        });
    }
}
