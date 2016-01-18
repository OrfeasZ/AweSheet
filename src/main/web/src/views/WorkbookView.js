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

import * as MessageType from '../constants/MessageTypes'

import Utils from '../util/Utils'

import SheetView from '../views/SheetView'

import Tabs from '../components/Workbook/Tabs'
import Tab from '../components/Workbook/Tab'

export default class WorkbookView extends Component
{
    render()
    {
        const { sheets, activeSheet, sheetProps, hasPopup } = this.props;

        var sheetKeys = Object.keys(sheets);

        if (sheetKeys.length == 0)
            return null;

        var self = this;

        return (
            <div className="workbook-view">
                <SheetView sheet={sheets[activeSheet]} sheetProps={sheetProps[activeSheet]} hasPopup={hasPopup} />
                <Tabs onCreateSheet={() => this.onCreateSheet()}>
                    {sheetKeys.map(function(value) {
                        let sheet = sheets[value];

                        return <Tab
                            key={sheet.id}
                            data={sheet.id}
                            onClick={(e) => self.onSheetSelect(e)}
                            onDelete={(e) => self.onSheetDelete(e)}
                            text={sheet.name}
                            canDelete={sheetKeys.length > 1}
                            active={sheet.id == activeSheet} />
                    })}
                </Tabs>
            </div>
        );
    }

    onCreateSheet()
    {
        Utils.dispatchMessage(MessageType.CREATE_SHEET, {});
    }

    onSheetSelect(id)
    {
        Utils.dispatchMessage(MessageType.SELECT_SHEET, {
            sheet: id
        });
    }

    onSheetDelete(id)
    {
        Utils.dispatchMessage(MessageType.DELETE_SHEET, {
            sheet: id
        });
    }
}
