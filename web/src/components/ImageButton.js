import React, { Component, PropTypes } from 'react'

export default class ImageButton extends Component
{
    render()
    {
        let className = 'image-btn';

        if (this.props.disabled)
            className += ' disabled';

        if (this.props.className)
            className += ' ' + this.props.className;

        return (
            <div className={className} onClick={(e) => this.onClick(e)}>
                <i className={'fa fa-' + this.props.imageClass} />
            </div>
        );
    }

    onClick(e)
    {
        e.preventDefault();

        if (this.props.onClick)
            this.props.onClick();
    }
}
