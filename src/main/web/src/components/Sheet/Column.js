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
