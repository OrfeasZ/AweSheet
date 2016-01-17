import React, { Component } from 'react'

import BaseContainer from './BaseContainer'

export default class App extends Component
{
    render()
    {
        return (<BaseContainer />);
    }

    componentDidMount()
    {
        window.addEventListener('resize', () => this.onResize());
    }

    componentWillUnmount()
    {
        window.removeEventListener('resize', () => this.onResize());
    }

    onResize()
    {
        // Force re-render every time the window is resized.
        this.forceUpdate();
    }
}