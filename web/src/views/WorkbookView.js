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
