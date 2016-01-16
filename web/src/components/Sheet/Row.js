import React, { Component, PropTypes } from 'react'

export default class Row extends Component
{
    render()
    {
        let value = null;

        if (!this.props.empty)
        {
            value = <div className="row-label" style={{ top: this.props.top + 'px', left: this.props.scroll + 'px' }}>{this.props.y + 1}</div>;
        }

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
