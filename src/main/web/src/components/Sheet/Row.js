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
