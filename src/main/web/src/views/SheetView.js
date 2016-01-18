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

import Toolbar from '../components/Sheet/Toolbar'
import Grid from '../components/Sheet/Grid'

export default class SheetView extends Component
{
    render()
    {
        const { sheet, sheetProps, hasPopup } = this.props;

        return (
            <div className="sheet-container">
                <Toolbar
                    sheet={sheet.id}
                    cells={sheet.cells}
                    selectedCells={sheetProps.selectedCells}
                    editingCell={sheetProps.editingCell} />
                <div className="sheet">
                    <Grid
                        id={sheet.id}
                        cells={sheet.cells}
                        selectedCells={sheetProps.selectedCells}
                        editingCell={sheetProps.editingCell}
                        maxColumn={sheet.maxColumn}
                        maxRow={sheet.maxRow}
                        hasPopup={hasPopup} />
                </div>
            </div>
        );
    }
}
