import React, { Component, PropTypes } from 'react'

import ImageButton from '../ImageButton'

export default class Tabs extends Component
{
    render()
    {
        return (
            <div className="tab-container">
                <div className="tabs">
                    {this.props.children}
                </div>
                <ImageButton className="create-sheet" imageClass="plus" onClick={() => this.onCreateSheet()} />
            </div>
        );
    }

    onCreateSheet()
    {
        if (this.props.onCreateSheet)
            this.props.onCreateSheet();
    }
}
