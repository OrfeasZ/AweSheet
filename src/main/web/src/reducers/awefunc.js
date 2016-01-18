import {
    SET_FUNCTIONS
} from '../constants/ActionTypes'

const initialState = {
    functions: {}
};

export default function awefunc(state = initialState, action)
{
    switch (action.type)
    {
        case SET_FUNCTIONS:
        {
            return {
                functions: action.functions
            };
        }

        default:
        {
            return state;
        }
    }
}