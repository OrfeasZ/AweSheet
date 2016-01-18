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
