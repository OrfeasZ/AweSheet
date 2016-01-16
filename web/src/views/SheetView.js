import React, { Component, PropTypes } from 'react'

import Toolbar from '../components/Sheet/Toolbar'
import Grid from '../components/Sheet/Grid'

export default class SheetView extends Component
{
    render()
    {
        const { sheet, sheetProps } = this.props;

        return (
            <div className="sheet-container">
                <Toolbar cells={sheet.cells} selectedCell={sheetProps.selectedCell} />
                <div className="sheet">
                    <Grid
                        id={sheet.id}
                        cells={sheet.cells}
                        selectedCell={sheetProps.selectedCell}
                        maxColumn={sheet.maxColumn}
                        maxRow={sheet.maxRow} />
                </div>
            </div>
        );
    }
}
