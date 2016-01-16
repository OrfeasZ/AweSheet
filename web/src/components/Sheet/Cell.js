import React, { Component, PropTypes } from 'react'

export default class Cell extends Component
{
    render()
    {
        let className = 'cell';

        if (this.props.selected)
            className += ' selected';

        let value = this.props.cell ? (<span>{this.props.cell.displayValue}</span>) : null;

        return (
            <div className={className}>
                {value}
            </div>
        );
    }
}
