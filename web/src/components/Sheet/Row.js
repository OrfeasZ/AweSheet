import React, { Component, PropTypes } from 'react'

export default class Row extends Component
{
    render()
    {
        let value = null;

        if (!this.props.empty)
        {
            value = <div className="row-label" data-y={this.props.y} style={{ left: this.props.scroll + 'px', height: this.props.size + 'px', lineHeight: this.props.size + 'px' }}>{this.props.y + 1}</div>;
        }

        let className = 'row';

        if (this.props.empty)
            className += ' empty';

        if (this.props.selected)
            className += ' selected';

        return (
            <div className={className} style={{ height: this.props.size + 'px' }}>
                {value}
                {this.props.children}
            </div>
        );
    }
}
