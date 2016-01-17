import React, { Component, PropTypes } from 'react'

import SheetView from '../views/SheetView'

import Tabs from '../components/Workbook/Tabs'
import Tab from '../components/Workbook/Tab'

export default class WorkbookView extends Component
{
    render()
    {
        const { sheets, activeSheet, sheetProps } = this.props;

        var sheetKeys = Object.keys(sheets);

        if (sheetKeys.length == 0)
            return null;

        var self = this;

        return (
            <div className="workbook-view">
                <SheetView sheet={sheets[activeSheet]} sheetProps={sheetProps[activeSheet]} />
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
        // TODO: Dispatch UI event.
    }

    onSheetSelect(id)
    {
        // TODO: Dispatch UI event.
    }

    onSheetDelete(id)
    {
        // TODO: Dispatch UI event.
    }
}
