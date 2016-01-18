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
