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

export default function team(state = initialState, action)
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
            return initialState;
        }
    }
}