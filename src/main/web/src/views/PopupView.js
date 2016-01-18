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

import * as PopupType from '../constants/PopupTypes'
import * as ActionType from '../constants/ActionTypes'

import CreateChartView from './CreateChartView'
import FunctionView from './FunctionView'
import HelpView from './HelpView'
import MessageView from './MessageView'
import SaveDiscardView from './SaveDiscardView'
import ChartView from './ChartView'
import AboutView from './AboutView'

export default class PopupView extends Component
{
    render()
    {
        const { popup, awefunc, help, workbook } = this.props;

        if (!popup.hasPopup)
            return null;

        let popupElement = null;

        switch (popup.currentPopup)
        {
            case PopupType.CREATE_CHART_POPUP:
                popupElement = <CreateChartView data={popup.popupData} />;
                break;

            case PopupType.FUNCTION_POPUP:
                popupElement = <FunctionView data={popup.popupData} awefunc={awefunc} workbook={workbook} />;
                break;

            case PopupType.HELP_POPUP:
                popupElement = <HelpView data={popup.popupData} help={help} />;
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

            case PopupType.ABOUT_POPUP:
                popupElement = <AboutView data={popup.popupData} />;
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
