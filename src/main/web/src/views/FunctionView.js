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

import * as ActionType from '../constants/ActionTypes'
import * as MessageType from '../constants/MessageTypes'
import * as ChartType from '../constants/ChartTypes'

import Utils from '../util/Utils'

export default class FunctionView extends Component
{
    constructor(props)
    {
        super(props);

        var firstFunction;

        for (firstFunction in this.props.awefunc.functions)
            break;

        this.state = {
            selectedFunction: firstFunction,
            currentArguments: 1,
            valid: true
        };
    }


    render()
    {
        const { awefunc } = this.props;

        let functions = [];

        for (let aweFunc in awefunc.functions)
            functions.push(<option key={aweFunc}>{aweFunc}</option>);

        let selectedFunction = this.state.selectedFunction;
        selectedFunction += '(';

        if (awefunc.functions[this.state.selectedFunction].arguments.length == 0)
            selectedFunction += '...';
        else
            selectedFunction += awefunc.functions[this.state.selectedFunction].arguments.join(', ');

        selectedFunction += ')';

        let functionDescription = awefunc.functions[this.state.selectedFunction].description;

        // Arguments.
        let argumentLabels = [];

        if (awefunc.functions[this.state.selectedFunction].arguments.length == 0)
        {
            for (let i = 0; i < this.state.currentArguments; ++i)
                argumentLabels.push(<label key={i}><span>{'Argument ' + (i + 1) + ':'}</span><input ref={'arg' + i} type="text" /></label>);

            argumentLabels.push(<label key={this.state.currentArguments}><span></span><div className="popup-button" onClick={(e) => this.onAddArgument(e)}>Add Argument</div></label>);
        }
        else
        {
            for (let i = 0; i < awefunc.functions[this.state.selectedFunction].arguments.length; ++i)
            {
                let argumentLabel = awefunc.functions[this.state.selectedFunction].arguments[i];
                argumentLabel = argumentLabel.substring(0, 1).toUpperCase() + argumentLabel.substring(1);
                argumentLabel += ':';
                argumentLabels.push(<label key={i}><span>{argumentLabel}</span><input ref={'arg' + i} type="text"/></label>);
            }
        }

        let className = 'content';

        if (!this.state.valid)
            className += ' invalid';

        return (
            <div className="popup">
                <div className="header">
                    Insert a Function
                </div>
                <div className={className}>
                    <label>
                        <span>Select Function:</span>
                        <select onChange={(e) => this.onSelectChange(e)}>
                            {functions}
                        </select>
                    </label>
                    <label>
                        <span></span>
                        <p>
                            <strong>{selectedFunction}</strong><br/>
                            {functionDescription}
                        </p>
                    </label>
                    <hr/>
                    {argumentLabels}
                    <div className="popup-button" onClick={(e) => this.onCreate(e)}>Insert Function</div>
                    <div className="popup-button" onClick={(e) => this.onClose(e)}>Cancel</div>
                </div>
            </div>
        );
    }

    onClose(e)
    {
        store.dispatch({
            type: ActionType.CLOSE_POPUP
        });
    }

    onCreate(e)
    {
        const { workbook, awefunc } = this.props;

        // Get the selected cell.
        let selectedCell = workbook.sheetProps[workbook.activeSheet].selectedCells[0];

        // Calculate function value.
        let functionValue = '=' + this.state.selectedFunction + '(';

        // Populate arguments.
        let argumentCount = awefunc.functions[this.state.selectedFunction].arguments.length == 0 ?
            this.state.currentArguments : awefunc.functions[this.state.selectedFunction].arguments.length;

        let functionArguments = [];

        for (let i = 0; i < argumentCount; ++i)
        {
            let argumentValue = this.refs['arg' + i].value.trim();

            if (argumentValue.length == 0)
            {
                this.setState({
                    valid: false
                });

                return;
            }

            if (argumentValue.substring(0, 1) == '=')
            {
                functionArguments.push(argumentValue);
                continue;
            }

            if (isNaN(argumentValue))
            {
                functionArguments.push('"' + argumentValue.replace('"', '\\"') + '"');
                continue;
            }

            functionArguments.push(argumentValue);
        }

        // Combine it all together.
        functionValue += functionArguments.join(', ');
        functionValue += ')';

        // Set the cell value.
        Utils.dispatchMessage(MessageType.SET_CELL_VALUE, {
            sheet: workbook.activeSheet,
            cellX: selectedCell[0],
            cellY: selectedCell[1],
            value: functionValue
        });

        // Close the popup.
        store.dispatch({
            type: ActionType.CLOSE_POPUP
        });
    }

    onSelectChange(e)
    {
        let selectedFunction = e.target.options[e.target.selectedIndex];

        this.setState({
            selectedFunction: selectedFunction.innerHTML,
            currentArguments: 1,
            valid: true
        });
    }

    onAddArgument(e)
    {
        this.setState({
            currentArguments: this.state.currentArguments + 1
        });
    }
}
