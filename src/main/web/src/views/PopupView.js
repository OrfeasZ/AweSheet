import React, { Component, PropTypes } from 'react'

import * as PopupType from '../constants/PopupType'
import * as ActionType from '../constants/ActionTypes'

import CreateChartView from './CreateChartView'
import FunctionView from './FunctionView'
import HelpView from './HelpView'
import MessageView from './MessageView'
import SaveDiscardView from './SaveDiscardView'
import ChartView from './ChartView'

export default class PopupView extends Component
{
    render()
    {
        const { popup } = this.props;

        if (!popup.hasPopup)
            return null;

        let popupElement = null;

        switch (popup.currentPopup)
        {
            case PopupType.CREATE_CHART_POPUP:
                popupElement = <CreateChartView data={popup.popupData} />;
                break;

            case PopupType.FUNCTION_POPUP:
                popupElement = <FunctionView data={popup.popupData} />;
                break;

            case PopupType.HELP_POPUP:
                popupElement = <HelpView data={popup.popupData} />;
                break;

            case PopupType.MESSAGE_POPUP:
                popupElement = <MessageView data={popup.popupData} />;
                break;

            case PopupType.SAVE_DISCARD_POPUP:
                popupElement = <SaveDiscardView data={popup.popupData} />;
                break;

            case PopupType.VIEW_CHART_POPUP:
                popupElement = <ChartView data={popup.popupData} />;
                break;
        }

        if (popupElement == null)
            return null;

        return (
            <div className="popup-view" onClick={(e) => this.onClick(e)}>
                {popupElement}
            </div>
        );
    }

    onClick(e)
    {
        if (e.target.className != 'popup-view')
            return;

        store.dispatch({
            type: ActionType.CLOSE_POPUP
        });
    }
}
