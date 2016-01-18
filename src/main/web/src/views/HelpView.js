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

import * as ActionType from '../constants/ActionTypes'

import Sidebar from '../components/Help/Sidebar'

export default class HelpView extends Component
{
    constructor(props)
    {
        super(props);

        this.state = {
            selected: 0
        };
    }

    render()
    {
        const { help } = this.props;

        let articleContent = help.articles[this.state.selected].content.replace(/\n/g, "<br/>");

        return (
            <div className="popup">
                <div className="header">
                    Documentation
                </div>
                <div className="content">
                    <Sidebar articles={help.articles} selected={this.state.selected} onSelected={(id) => this.onSelected(id)} />
                    <div className="article" dangerouslySetInnerHTML={{__html: articleContent}}></div>
                </div>
            </div>
        );
    }

    onSelected(id)
    {
        this.setState({
            selected: id
        });
    }
}
