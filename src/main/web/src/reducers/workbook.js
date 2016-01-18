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
    SET_SHEET,
    REMOVE_SHEET,
    SET_ACTIVE_SHEET,
    SET_SELECTED_CELLS,
    SET_EDITING_CELL,
    SET_MAX_COLUMN,
    SET_MAX_ROW,
    SET_CELL,
    REMOVE_CELL,
    CLEAR_SHEETS,
} from '../constants/ActionTypes'

const initialState = {
    sheets: {},
    sheetProps: {},
    activeSheet: 0
};

export default function workbook(state = initialState, action)
{
    switch (action.type)
    {
        case SET_SHEET:
        {
            let finalState = {
                sheets: state.sheets,
                sheetProps: state.sheetProps,
                activeSheet: state.activeSheet
            };

            finalState.sheets[action.sheet.id] = action.sheet;

            if (!(action.sheet.id in finalState.sheetProps))
            {
                finalState.sheetProps[action.sheet.id] = {
                    selectedCells: [],
                    editingCell: null
                };
            }

            return finalState;
        }

        case CLEAR_SHEETS:
        {
            return {
                sheets: {},
                sheetProps: {},
                activeSheet: 0
            };
        }

        case REMOVE_SHEET:
        {
            let finalState = {
                sheets: state.sheets,
                sheetProps: state.sheetProps,
                activeSheet: state.activeSheet
            };

            delete finalState.sheets[action.sheet];
            delete finalState.sheetProps[action.sheet];

            return finalState;
        }

        case SET_ACTIVE_SHEET:
        {
            return {
                sheets: state.sheets,
                sheetProps: state.sheetProps,
                activeSheet: action.sheet
            };
        }

        case SET_SELECTED_CELLS:
        {
            if (!(action.sheet in state.sheetProps))
                return state;

            let finalState = {
                sheets: state.sheets,
                sheetProps: state.sheetProps,
                activeSheet: state.activeSheet
            };

            finalState.sheetProps[action.sheet].selectedCells = action.cells;

            return finalState;
        }

        case SET_EDITING_CELL:
        {
            if (!(action.sheet in state.sheetProps))
                return state;

            let finalState = {
                sheets: state.sheets,
                sheetProps: state.sheetProps,
                activeSheet: state.activeSheet
            };

            finalState.sheetProps[action.sheet].editingCell = action.cell;

            return finalState;
        }

        case SET_MAX_COLUMN:
        {
            if (!(action.sheet in state.sheets))
                return state;

            let finalState = {
                sheets: state.sheets,
                sheetProps: state.sheetProps,
                activeSheet: state.activeSheet
            };

            finalState.sheets[action.sheet].maxColumn = action.maxColumn;

            return finalState;
        }

        case SET_MAX_ROW:
        {
            if (!(action.sheet in state.sheets))
                return state;

            let finalState = {
                sheets: state.sheets,
                sheetProps: state.sheetProps,
                activeSheet: state.activeSheet
            };

            finalState.sheets[action.sheet].maxRow = action.maxRow;

            return finalState;
        }

        case SET_CELL:
        {
            if (!(action.cell.sheet in state.sheets))
                return state;

            let finalState = {
                sheets: state.sheets,
                sheetProps: state.sheetProps,
                activeSheet: state.activeSheet
            };

            finalState.sheets[action.cell.sheet].cells[action.cell.x + 'x' + action.cell.y] = action.cell;

            return finalState;
        }

        case REMOVE_CELL:
        {
            let cell = action.x + 'x' + action.y;

            if (!(action.sheet in state.sheets) || !(cell in state.sheets[action.sheet].cells))
                return state;

            let finalState = {
                sheets: state.sheets,
                sheetProps: state.sheetProps,
                activeSheet: state.activeSheet
            };

            delete finalState.sheets[action.sheet].cells[cell];

            return finalState;
        }

        default:
            return state;
    }
}