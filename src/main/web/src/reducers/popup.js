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

import {
    SHOW_POPUP,
    CLOSE_POPUP,
    SET_POPUP_DATA
} from '../constants/ActionTypes'

const initialState = {
    hasPopup: false,
    currentPopup: 0,
    popupData: {}
};

export default function popup(state = initialState, action)
{
    switch (action.type)
    {
        case SHOW_POPUP:
        {
            return {
                hasPopup: true,
                currentPopup: action.popup,
                popupData: action.data
            };
        }

        case CLOSE_POPUP:
        {
            return {
                hasPopup: false,
                currentPopup: 0,
                popupData: {}
            };
        }

        case SET_POPUP_DATA:
        {
            return {
                hasPopup: state.hasPopup,
                currentPopup: state.currentPopup,
                popupData: {
                    ...state.popupData,
                    ...action.data
                }
            };
        }

        default:
        {
            return state;
        }
    }
}