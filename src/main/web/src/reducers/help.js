import {
    SET_HELP_ARTICLES
} from '../constants/ActionTypes'

const initialState = {
    articles: {}
};

export default function help(state = initialState, action)
{
    switch (action.type)
    {
        case SET_HELP_ARTICLES:
        {
            return {
                articles: action.articles
            };
        }

        default:
        {
            return state;
        }
    }
}