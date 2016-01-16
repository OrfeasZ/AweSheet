import React, { Component, PropTypes } from 'react'

export default class Tabs extends Component
{
    render()
    {
        return (
            <div className="tab-container">
                {this.props.children}
            </div>
        );
    }
}
