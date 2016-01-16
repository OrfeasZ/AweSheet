import React, { Component, PropTypes } from 'react'

export default class Row extends Component
{
    render()
    {
        let value = this.props.empty ?
            null :
            <div className="row-label">{this.props.y}</div>;

        let className = 'row';

        if (this.props.empty)
            className += ' empty';

        if (this.props.selected)
            className += ' selected';

        return (
            <div className={className}>
                {value}
                {this.props.children}
            </div>
        );
    }
}
