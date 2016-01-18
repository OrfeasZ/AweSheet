import { combineReducers } from 'redux'
import { default as workbook } from './workbook'
import { default as popup } from './popup'

export default combineReducers({
    workbook,
    popup
});