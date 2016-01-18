import React, { Component, PropTypes } from 'react'

import * as ActionType from '../constants/ActionTypes'

export default class ChartView extends Component
{
    render()
    {
        const { data } = this.props;

        return (
            <div className="popup">
                <div className="header">
                    Chart
                </div>
                <div className="content">
                    <div className="chart-view">
                        <img src={"data:image/png;base64," + data.imageData} />
                    </div>
                    <div className="popup-button" onClick={(e) => this.onClose(e)}>Close</div>
                    <div className="popup-button" onClick={(e) => this.onSave(e)}>Save as Image</div>
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

    onSave(e)
    {
    }
}
