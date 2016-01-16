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

// TODO: Remove after testing.
window.store = store;

import * as ActionType from './constants/ActionTypes'
