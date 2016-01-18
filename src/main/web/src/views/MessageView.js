import React, { Component, PropTypes } from 'react'

import * as ActionType from '../constants/ActionTypes'

export default class MessageView extends Component
{
    render()
    {
        const { data } = this.props;

        return (
            <div className="popup">
                <div className="header">
                    {data.title}
                </div>
                <div className="content">
                    <p>{data.message}</p>
                    <div className="popup-button" onClick={(e) => this.onClose(e)}>Close</div>
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
}
