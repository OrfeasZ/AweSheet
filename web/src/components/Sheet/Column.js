import React, { Component, PropTypes } from 'react'

export default class Column extends Component
{
    render()
    {
        // TODO: Translate to letter.
        let value = this.props.empty ? null : this.props.x;

        let className = 'column';

        if (this.props.empty)
            className += ' empty';

        if (this.props.selected)
            className += ' selected';

        return (
            <div className={className}>
                {value}
            </div>
        );
    }
}
