import React from 'react'
import { render } from 'react-dom'
import { createStore } from 'redux'
import { Provider } from 'react-redux'
import logger from 'redux-logger'
import thunk from 'redux-thunk'
import reducer from './reducers'
import App from './containers/App'

const store = createStore(reducer);

render(
    <Provider store={store}>
        <App />
    </Provider>,
    document.getElementById('root')
);

window.store = store;

// TODO: Remove after testing.
import * as ActionType from './constants/ActionTypes'

store.dispatch({
    type: ActionType.SET_SHEET,
    sheet: {
        id: 0,
        name: 'Sheet 1',
        maxColumn: 14,
        maxRow: 14,
        cells: {
            '0x5': {
                displayValue: 'hi',
                value: 'bye'
            }
        }
    }
});

store.dispatch({
    type: ActionType.SET_SHEET,
    sheet: {
        id: 1,
        name: 'Sheet 2',
        maxColumn: 14,
        maxRow: 14,
        cells: {}
    }
});

store.dispatch({
    type: ActionType.SET_SHEET,
    sheet: {
        id: 2,
        name: 'Sheet 3',
        maxColumn: 14,
        maxRow: 14,
        cells: {}
    }
});