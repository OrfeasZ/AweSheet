import React, { Component, PropTypes } from 'react'

export default class Sidebar extends Component
{
    render()
    {
        const { articles, selected } = this.props;

        let uiArticles = [];

        for (let id in articles)
        {
            let className = 'sidebar-item';

            if (selected == id)
                className += ' selected';

            uiArticles.push(<div key={id} className={className} onClick={() => this.onClick(id)}>{articles[id].title}</div>);
        }

        return (
            <div className="help-sidebar">
                {uiArticles}
            </div>
        );
    }

    onClick(id)
    {
        if (this.props.onSelected)
            this.props.onSelected(id);
    }
}
