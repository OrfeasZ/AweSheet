import { combineReducers } from 'redux'
import { default as workbook } from './workbook'
import { default as popup } from './popup'
import { default as awefunc } from './awefunc'
import { default as help } from './help'

export default combineReducers({
    workbook,
    popup,
    help,
    awefunc
});