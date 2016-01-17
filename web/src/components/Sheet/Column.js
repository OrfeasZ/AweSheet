import React, { Component, PropTypes } from 'react'

import Utils from '../../util/Utils'

export default class Column extends Component
{
    render()
    {
        let value = this.props.empty ? null : Utils.getColumnName(this.props.x);

        let className = 'column';

        if (this.props.empty)
            className += ' empty';

        if (this.props.selected)
            className += ' selected';

        return (
            <div
                data-x={!this.props.empty ? this.props.x : -1}
                className={className}
                style={{ width: this.props.size + 'px', left: this.props.left + 'px', top: this.props.scroll + 'px' }} >
                {value}
            </div>
        );
    }
}
