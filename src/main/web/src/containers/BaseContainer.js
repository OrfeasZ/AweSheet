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