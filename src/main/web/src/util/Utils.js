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

import * as MessageType from '../constants/MessageTypes'

export default class Utils
{
    static getColumnName(x)
    {
        var ordA = 'a'.charCodeAt(0);
        var ordZ = 'z'.charCodeAt(0);

        var len = ordZ - ordA + 1;

        var name = "";

        while (x >= 0)
        {
            name = String.fromCharCode(x % len + ordA) + name;
            x = Math.floor(x / len) - 1;
        }

        return name.toUpperCase();
    }

    static dispatchMessage(type, data)
    {
        let message = {
            ...data,
            type: type
        };

        window.aweQuery({ request: this.getMessageClass(type) + ';' + JSON.stringify(message) });
    }

    static getMessageClass(type)
    {
        let className = '';

        switch (type)
        {
            case MessageType.SET_CELL_VALUE:
                className = 'SetCellValueMessage';
                break;

            case MessageType.CREATE_SHEET:
                className = 'CreateSheetMessage';
                break;

            case MessageType.SELECT_SHEET:
                className = 'SelectSheetMessage';
                break;

            case MessageType.DELETE_SHEET:
                className = 'DeleteSheetMessage';
                break;

            case MessageType.SET_SELECTED_CELLS:
                className = 'SetSelectedCellsMessage';
                break;

            case MessageType.CREATE_BAR_CHART:
                className = 'CreateBarChartMessage';
                break;

            case MessageType.CREATE_LINE_CHART:
                className = 'CreateLineChartMessage';
                break;

            case MessageType.SAVE_CHART_IMAGE:
                className = 'SaveChartImageMessage';
                break;

            default:
                className = 'UIMessage';
                break;
        }

        return 'com.awesheet.messages.' + className;
    }
}