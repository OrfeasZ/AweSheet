import React, { Component, PropTypes } from 'react'

export default class Tab extends Component
{
    render()
    {
        let className = 'tab';

        if (this.props.active)
            className += ' active';

        let closeButton = null;

        if (this.props.canDelete && !this.props.active)
            closeButton = <span ref="close" className="close" onClick={(e) => this.onDelete(e)}>x</span>;

        return (
            <div className={className} onClick={(e) => this.onClick(e)}>
                <span className="text">{this.props.text}</span>
                {closeButton}
            </div>
        );
    }

    onClick(e)
    {
        e.preventDefault();

        if (e.target == this.refs.close)
            return;

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
