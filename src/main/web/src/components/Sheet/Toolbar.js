/*
 * AweSheet - Simple Open-Source Spreadsheet Editor
 * Copyright (c) 2015 - 2016, Orfeas - Ioannis Zafeiris, Nikolaos Fylakis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import React, { Component, PropTypes } from 'react'

import * as MessageType from '../../constants/MessageTypes'
import * as ActionType from '../../constants/ActionTypes'
import * as PopupType from '../../constants/PopupTypes'
import * as ChartType from '../../constants/ChartTypes'

import Utils from '../../util/Utils'

import ImageButton from '../ImageButton'

export default class Toolbar extends Component
{
    constructor(props)
    {
        super(props);

        this.state = {
            editValue: '',
            originalValue: '',
            editingCell: null
        };

        if (props.selectedCells.length == 1)
        {
            this.state.originalValue = props.cells[props.selectedCells[0][0] + 'x' + props.selectedCells[0][1]] ? props.cells[props.selectedCells[0][0] + 'x' + props.selectedCells[0][1]].value : '';
            this.state.editValue = this.state.originalValue;
            this.state.editingCell = [props.selectedCells[0][0],  props.selectedCells[0][1]];
        }
    }

    render()
    {
        const { cells, selectedCells, editingCell } = this.props;

        let selectedValue = '';
        let editValue = '';
        let functionEnabled = false;
        let buttonsEnabled = false;
        let chartsEnabled = false;

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

            if (maxCell[0] - minCell[0] >= 1 && maxCell[1] - minCell[1] >= 1)
                chartsEnabled = true;

            selectedValue = Utils.getColumnName(minCell[0]) + (minCell[1] + 1) + ' x ' + Utils.getColumnName(maxCell[0]) + (maxCell[1] + 1);
        }
        else if (selectedCells.length == 1)
        {
            selectedValue = Utils.getColumnName(selectedCells[0][0]) + (selectedCells[0][1] + 1);
            editValue = cells[selectedCells[0][0] + 'x' + selectedCells[0][1]] ? cells[selectedCells[0][0] + 'x' + selectedCells[0][1]].value : '';
            functionEnabled = true;
        }

        if (editingCell != null || this.state.editValue != this.state.originalValue)
            buttonsEnabled = true;

        return (
            <div className="toolbar">
                <input
                    type="text"
                    className="selected-cell"
                    value={selectedValue}
                    readOnly={true} />
                <div className="toolbar-buttons">
                    <ImageButton className="bar-chart-button" imageClass="bar-chart" disabled={!chartsEnabled} onClick={(e) => this.onBarChartClick(e)} />
                    <ImageButton className="line-chart-button" imageClass="line-chart" disabled={!chartsEnabled} onClick={(e) => this.onLineChartClick(e)} />
                    <ImageButton className="cancel-button" imageClass="times" disabled={!buttonsEnabled} onClick={(e) => this.onCancelClick(e)} />
                    <ImageButton className="confirm-button" imageClass="check" disabled={!buttonsEnabled} onClick={(e) => this.onConfirmClick(e)} />
                    <ImageButton className="function-button" imageClass="bolt" disabled={!functionEnabled} onClick={(e) => this.onFunctionClick(e)} />
                </div>
                <input
                    ref="value"
                    type="text"
                    className="value-input"
                    defaultValue={editValue}
                    onChange={(e) => this.onChange(e)}
                    onKeyDown={(e) => this.onInputKeyDown(e)}
                    readOnly={this.state.editingCell === null} />
            </div>
        );
    }

    componentWillReceiveProps(nextProps)
    {
        if (this.props.selectedCells == nextProps.selectedCells && this.props.editingCell == nextProps.editingCell)
            return;

        if (this.props.selectedCells != nextProps.selectedCells && nextProps.selectedCells.length == 1)
        {
            this.refs.value.value = nextProps.cells[nextProps.selectedCells[0][0] + 'x' + nextProps.selectedCells[0][1]] ? nextProps.cells[nextProps.selectedCells[0][0] + 'x' + nextProps.selectedCells[0][1]].value : '';

            this.setState({
                editValue: this.refs.value.value,
                originalValue: this.refs.value.value,
                editingCell: [nextProps.selectedCells[0][0],  nextProps.selectedCells[0][1]]
            });
        }
    }

    onBarChartClick(e)
    {
        store.dispatch({
            type: ActionType.SHOW_POPUP,
            popup: PopupType.CREATE_CHART_POPUP,
            data: {
                type: ChartType.BAR_CHART_TYPE
            }
        });
    }

    onLineChartClick(e)
    {
        store.dispatch({
            type: ActionType.SHOW_POPUP,
            popup: PopupType.CREATE_CHART_POPUP,
            data: {
                type: ChartType.LINE_CHART_TYPE
            }
        });
    }

    onFunctionClick(e)
    {
        store.dispatch({
            type: ActionType.SHOW_POPUP,
            popup: PopupType.FUNCTION_POPUP
        });
    }

    onCancelClick(e)
    {
        this.refs.value.value = this.state.originalValue;

        this.refs.value.blur();

        this.setState({
            editValue: this.state.originalValue
        });
    }

    onConfirmClick(e)
    {
        if (this.state.editingCell === null)
            return;

        this.refs.value.blur();

        Utils.dispatchMessage(MessageType.SET_CELL_VALUE, {
            sheet: this.props.sheet,
            cellX: this.state.editingCell[0],
            cellY: this.state.editingCell[1],
            value: this.refs.value.value
        });

        store.dispatch({
            type: ActionType.SET_EDITING_CELL,
            sheet: this.props.sheet,
            cell: null
        });
    }

    onChange(e)
    {
        this.setState({
            editValue: this.refs.value.value
        });
    }

    onInputKeyDown(event)
    {
        if (this.state.editingCell === null)
            return;

        // ESC pressed; discard changes.
        if (event.keyCode == 27)
        {
            this.refs.value.value = this.state.originalValue;

            this.refs.value.blur();

            this.setState({
                editValue: this.state.originalValue
            });

            store.dispatch({
                type: ActionType.SET_EDITING_CELL,
                sheet: this.props.sheet,
                cell: null
            });

            return;
        }

        // Enter pressed; persist changes.
        if (event.keyCode == 13)
        {
            this.refs.value.blur();

            Utils.dispatchMessage(MessageType.SET_CELL_VALUE, {
                sheet: this.props.sheet,
                cellX: this.state.editingCell[0],
                cellY: this.state.editingCell[1],
                value: this.refs.value.value
            });

            store.dispatch({
                type: ActionType.SET_EDITING_CELL,
                sheet: this.props.sheet,
                cell: null
            });
        }
    }
}