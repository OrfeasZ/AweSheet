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

import React, { Component, PropTypes } from 'react'
import { connect } from 'react-redux'

import WorkbookView from '../views/WorkbookView'
import PopupView from '../views/PopupView'

class BaseContainer extends Component
{
    render()
    {
        const { workbook, popup, awefunc, help } = this.props;

        return (
            <div className="app-view">
                <WorkbookView sheets={workbook.sheets} activeSheet={workbook.activeSheet} sheetProps={workbook.sheetProps} hasPopup={popup.hasPopup} />
                <PopupView popup={popup} awefunc={awefunc} help={help} workbook={workbook} />
            </div>
        );
    }
}

const mapStateToProps = (state) => { return state; };

export default connect(
    mapStateToProps
)(BaseContainer);