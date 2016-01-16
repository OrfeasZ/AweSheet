import {
    ADD_SHEET
} from '../constants/ActionTypes'

const initialState = {
    sheets: []
};

export default function team(state = initialState, action)
{
    switch (action.type)
    {
        case ADD_SHEET:
        {
            let finalState = {
                sheets: []
            };

            return finalState;
        }

        default:
            return state;
    }
}