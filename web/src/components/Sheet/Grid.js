import React, { Component, PropTypes } from 'react'

import Column from './Column'
import Cell from './Cell'
import Row from './Row'

export default class Grid extends Component
{
    render()
    {
        const { cells, maxRow, maxColumn, selectedCell } = this.props;

        let head = [];
        let body = [];

        head.push(<Column key={-1} empty={true} />);

        for (let x = 0; x < maxColumn; ++x)
            head.push(<Column key={x} x={x} selected={selectedCell[0] == x} />);

        // Populate rows.
        for (let y = 0; y < maxRow; ++y)
        {
            let rowCells = [];

            // Populate cells.
            for (let x = 0; x < maxColumn; ++x)
                rowCells.push(<Cell key={x + 'x' + y} cell={cells[x + 'x' + y]} x={x} y={y} selected={selectedCell[0] == x && selectedCell[1] == y} />);

            body.push(<Row key={y} y={y} selected={selectedCell[1] == y}>{rowCells}</Row>);
        }

        return (
            <table className="sheet-grid">
                <thead>
                    <tr className="row">
                        {head}
                    </tr>
                </thead>
                <tbody>
                    {body}
                </tbody>
            </table>
        );
    }
}
