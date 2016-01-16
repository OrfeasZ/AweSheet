import React, { Component, PropTypes } from 'react'

export default class Column extends Component
{
    render()
    {
        // TODO: Translate to letter.
        let value = this.props.empty ? null : this.getColumnName();

        let className = 'column';

        if (this.props.empty)
            className += ' empty';

        if (this.props.selected)
            className += ' selected';

        return (
            <th className={className}>
                {value}
            </th>
        );
    }

    getColumnName()
    {
        var x = this.props.x;

        var ordA = 'a'.charCodeAt(0);
        var ordZ = 'z'.charCodeAt(0);

        var len = ordZ - ordA + 1;

        var name = "";

        while(x >= 0)
        {
            name = String.fromCharCode(x % len + ordA) + name;
            x = Math.floor(x / len) - 1;
        }

        return name;
    }
}
