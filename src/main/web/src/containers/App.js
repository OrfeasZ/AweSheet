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