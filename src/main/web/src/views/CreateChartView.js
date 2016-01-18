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

export default class CreateChartView extends Component
{
    render()
    {
        const { data } = this.props;

        let title = '';

        if (data.type == ChartType.BAR_CHART_TYPE)
            title = 'Create a Bar Chart';
        else
            title = 'Create a Line Chart';

        return (
            <div className="popup">
                <div className="header">
                    {title}
                </div>
                <div className="content">
                    <label><span>Chart Title:</span><input ref="title" type="text" /></label>
                    <label><span>X Axis Title:</span><input ref="xaxis" type="text" /></label>
                    <label><span>Y Axis Title:</span><input ref="yaxis" type="text" /></label>
                    <div className="popup-button" onClick={(e) => this.onCreate(e)}>Create Chart</div>
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
        Utils.dispatchMessage(this.props.data.type == ChartType.BAR_CHART_TYPE ? MessageType.CREATE_BAR_CHART : MessageType.CREATE_LINE_CHART, {
            title: this.refs.title.value,
            xaxis: this.refs.xaxis.value,
            yaxis: this.refs.yaxis.value
        });
    }
}
