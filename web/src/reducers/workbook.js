import {
    SET_SHEET,
    REMOVE_SHEET,
    SET_ACTIVE_SHEET,
    SET_SELECTED_CELL
} from '../constants/ActionTypes'

const initialState = {
    sheets: {},
    sheetProps: {},
    activeSheet: 0
};

export default function team(state = initialState, action)
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
                    selectedCell: [-1, -1]
                };
            }

            return finalState;
        }

        case REMOVE_SHEET:
        {
            let finalState = {
                sheets: state.sheets,
                sheetProps: state.sheetProps,
                activeSheet: state.activeSheet
            };

            delete finalState.sheets[action.id];
            delete finalState.sheetProps[action.id];

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

        case SET_SELECTED_CELL:
        {
            let finalState = {
                sheets: state.sheets,
                sheetProps: state.sheetProps,
                activeSheet: state.activeSheet
            };

            if (action.id in finalState.sheetProps)
                finalState.sheetProps[action.id].selectedCell = [ action.x, action.y ];

            return finalState;
        }

        default:
            return state;
    }
}