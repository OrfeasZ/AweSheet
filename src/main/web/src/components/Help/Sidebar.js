/*
 * AweSheet - Simple Open-Source Spreadsheet Editor
 * Copyright (c) 2015 - 2016, Orfeas - Ioannis Zafeiris, Nikolaos Fylakis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
