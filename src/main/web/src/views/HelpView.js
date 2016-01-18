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
