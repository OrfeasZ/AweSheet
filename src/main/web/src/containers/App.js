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

import React, { Component } from 'react'

import BaseContainer from './BaseContainer'

import * as MessageType from '../constants/MessageTypes'
import * as ActionType from '../constants/ActionTypes'
import * as PopupType from '../constants/PopupTypes'

import Utils from '../util/Utils'

export default class App extends Component
{
    constructor(props)
    {
        super(props);
    }

    render()
    {
        return (<BaseContainer />);
    }

    componentDidMount()
    {
        window.addEventListener('resize', () => this.onResize());
        window.addEventListener('keydown', (e) => this.onKeyDown(e));
    }

    componentWillUnmount()
    {
        window.removeEventListener('resize', () => this.onResize());
        window.removeEventListener('keydown', (e) => this.onKeyDown(e));
    }

    onResize()
    {
        // Force re-render every time the window is resized.
        this.forceUpdate();
    }

    onKeyDown(event)
    {
        // Handle shortcuts.
        // CTRL+C
        if (!event.shiftKey && !event.altKey && event.ctrlKey && event.keyCode == 66)
            Utils.dispatchMessage(MessageType.COPY_CELL, {});

        // CTRL+X
        if (!event.shiftKey && !event.altKey && event.ctrlKey && event.keyCode == 88)
            Utils.dispatchMessage(MessageType.CUT_CELL, {});

        // CTRL+V
        if (!event.shiftKey && !event.altKey && event.ctrlKey && event.keyCode == 86)
            Utils.dispatchMessage(MessageType.PASTE_CELL, {});

        // CTRL+N
        if (!event.shiftKey && !event.altKey && event.ctrlKey && event.keyCode == 78)
            Utils.dispatchMessage(MessageType.CREATE_WORKBOOK, {});

        // CTRL+S
        if (!event.shiftKey && !event.altKey && event.ctrlKey && event.keyCode == 83)
            Utils.dispatchMessage(MessageType.SAVE_WORKBOOK, {});

        // CTRL+SHIFT+S
        if (event.shiftKey && !event.altKey && event.ctrlKey && event.keyCode == 83)
            Utils.dispatchMessage(MessageType.SAVE_WORKBOOK_AS, {});

        // CTRL+O
        if (!event.shiftKey && !event.altKey && event.ctrlKey && event.keyCode == 79)
            Utils.dispatchMessage(MessageType.OPEN_WORKBOOK, {});

        // CTRL+W
        if (!event.shiftKey && !event.altKey && event.ctrlKey && event.keyCode == 87)
            Utils.dispatchMessage(MessageType.EXIT_APPLICATION, {});

        // F11
        if (!event.shiftKey && !event.altKey && !event.ctrlKey && event.keyCode == 122)
            Utils.dispatchMessage(MessageType.MAXIMIZE_APPLICATION, {});

        // F12
        if (!event.shiftKey && !event.altKey && !event.ctrlKey && event.keyCode == 123)
            window.store.dispatch({ type: ActionType.SHOW_POPUP, popup: PopupType.HELP_POPUP });
    }
}