import React, { Component, PropTypes } from 'react'

export default class Row extends Component
{
    render()
    {
        let value = this.props.empty ?
            null :
            <td className="row-label">{this.props.y + 1}</td>;

        let className = 'row';

        if (this.props.empty)
            className += ' empty';

        if (this.props.selected)
            className += ' selected';

        return (
            <tr className={className}>
                {value}
                {this.props.children}
            </tr>
        );
    }
}
