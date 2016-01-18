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

import * as ActionType from '../../constants/ActionTypes'
import * as MessageType from '../../constants/MessageTypes'

import Utils from '../../util/Utils'

export default class Cell extends Component
{
    constructor(props)
    {
        super(props);

        this.state = {
            inputSize: this.props.cell ? (this.props.cell.value.length * 1.1) : 1
        };
    }

    render()
    {
        let className = 'cell';

        if (this.props.selected)
            className += ' selected';

        let input = null;

        if (this.props.editing)
        {
            className += ' editing';
            input = <input
                ref="input"
                className="cell-input"
                data-x={this.props.x}
                data-y={this.props.y}
                type="text"
                size={this.state.inputSize}
                onChange={(e) => this.onValueChange(e)}
                onKeyDown={(e) => this.onInputKeyDown(e)}
                defaultValue={this.props.cell ? this.props.cell.value : ''} />;
        }

        let value = this.props.cell ? this.props.cell.displayValue : null;

        return (
            <div
                data-x={this.props.x}
                data-y={this.props.y}
                className={className}
                onDoubleClick={(e) => this.onDoubleClick(e)}
                onClick={(e) => this.onClick(e)}
                onKeyDown={(e) => this.onCellKeyDown(e)}
                style={{ width: this.props.size + 'px', height: this.props.height + 'px', lineHeight: this.props.height + 'px' }} >
                <span
                    className="cell-value"
                    data-x={this.props.x}
                    data-y={this.props.y}>
                    {value}
                </span>
                {input}
            </div>
        );
    }

    componentDidMount()
    {
        if (this.props.editing)
            this.refs.input.focus();
    }

    componentDidUpdate()
    {
        if (this.props.editing && this.refs.input)
            this.refs.input.focus();

        let inputSize = (this.refs.input ? (!this.refs.input.value.length ? 1 : (this.refs.input.value.length * 1.1)) :
            (this.props.cell ? this.props.cell.value.length : 1));

        if (inputSize != this.state.inputSize)
        {
            this.setState({
                inputSize: inputSize
            });
        }
    }

    componentWillReceiveProps(nextProps)
    {
        // Save cell value changes.
        if (this.props.editing && !nextProps.editing)
        {
            Utils.dispatchMessage(MessageType.SET_CELL_VALUE, {
                sheet: this.props.sheet,
                cellX: this.props.x,
                cellY: this.props.y,
                value: this.refs.input.value
            });
        }
    }

    onDoubleClick(event)
    {
        store.dispatch({
            type: ActionType.SET_EDITING_CELL,
            sheet: this.props.sheet,
            cell: [ this.props.x, this.props.y ]
        });
    }

    onClick(event)
    {
        if (this.props.editing)
            return;

        store.dispatch({
            type: ActionType.SET_EDITING_CELL,
            sheet: this.props.sheet,
            cell: null
        });
    }

    onCellKeyDown(event)
    {

    }

    onValueChange(event)
    {
        this.setState({
            inputSize: !event.target.value.length ? 1 : (event.target.value.length * 1.1)
        });
    }

    onInputKeyDown(event)
    {
        // ESC pressed; discard changes.
        if (event.keyCode == 27)
        {
            event.target.value = this.props.cell ? this.props.cell.value : '';

            this.setState({
                inputSize: !event.target.value.length ? 1 : (event.target.value.length * 1.1)
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
            store.dispatch({
                type: ActionType.SET_EDITING_CELL,
                sheet: this.props.sheet,
                cell: null
            });
        }
    }
}
