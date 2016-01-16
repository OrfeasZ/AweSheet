import React, { Component, PropTypes } from 'react'

export default class Tab extends Component
{
    render()
    {
        let className = 'tab';

        if (this.props.active)
            className += ' active';

        return (
            <div className={className}>
                <span className="text" onClick={(e) => this.onClick(e)}>{this.props.text}</span>
                <span className="close" onClick={(e) => this.onDelete(e)}>x</span>
            </div>
        );
    }

    onClick(e)
    {
        e.preventDefault();

        if (this.props.active || !this.props.onClick)
            return;

        this.props.onClick(this.props.data);
    }

    onDelete(e)
    {
        e.preventDefault();

        if (this.props.active || !this.props.onDelete || !this.props.canDelete)
            return;

        this.props.onDelete(this.props.data);
    }
}
