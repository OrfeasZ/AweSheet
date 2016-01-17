import React, { Component, PropTypes } from 'react'

import Utils from '../../util/Utils'

import ImageButton from '../ImageButton'

export default class Toolbar extends Component
{
    constructor(props)
    {
        super(props);

        this.state = {
            editValue: ''
        };
    }

    render()
    {
        const { cells, selectedCells, editingCell } = this.props;

        let selectedValue = '';
        let editValue = '';
        let functionEnabled = false;
        let buttonsEnabled = false;

        if (selectedCells.length > 1)
        {
            let minCell = selectedCells[0];
            let maxCell = selectedCells[0];

            for (let cell of selectedCells)
            {
                if (cell[0] < minCell[0] && cell[1] < minCell[1])
                    minCell = cell;

                if (cell[0] > maxCell[0] && cell[1] > maxCell[1])
                    maxCell = cell;
            }

            selectedValue = minCell[1] + Utils.getColumnName(minCell[0]) + ' x ' + maxCell[1] + Utils.getColumnName(maxCell[0]);
        }
        else if (selectedCells.length == 1)
        {
            selectedValue = selectedCells[0][1] + Utils.getColumnName(selectedCells[0][0]);
            editValue = cells[selectedCells[0][0] + 'x' + selectedCells[0][1]] ? cells[selectedCells[0][0] + 'x' + selectedCells[0][1]].value : '';
            functionEnabled = true;
        }

        if (editingCell != null || this.state.editValue.length > 0)
            buttonsEnabled = true;

        return (
            <div className="toolbar">
                <input
                    type="text"
                    className="selected-cell"
                    value={selectedValue} />
                <div className="toolbar-buttons">
                    <ImageButton className="cancel-button" imageClass="times" disabled={!buttonsEnabled} />
                    <ImageButton className="confirm-button" imageClass="check" disabled={!buttonsEnabled} />
                    <ImageButton className="function-button" imageClass="bolt" disabled={!functionEnabled} />
                </div>
                <input
                    ref="value"
                    type="text"
                    className="value-input"
                    value={editValue} />
            </div>
        );
    }
}