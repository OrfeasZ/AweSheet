import React, { Component, PropTypes } from 'react'
import * as ActionType from '../../constants/ActionTypes'

export default class Cell extends Component
{
    constructor(props)
    {
        super(props);

        this.state = {
            inputSize: this.props.cell ? (this.props.cell.value.length * 1.1) : 1
        };
    }

    render()
    {
        let className = 'cell';

        if (this.props.selected)
            className += ' selected';

        let input = null;

        if (this.props.editing)
        {
            className += ' editing';
            input = <input
                ref="input"
                className="cell-input"
                type="text"
                size={this.state.inputSize}
                onChange={(e) => this.onValueChange(e)}
                onKeyDown={(e) => this.onInputKeyDown(e)}
                defaultValue={this.props.cell ? this.props.cell.value : ''} />;
        }

        let value = this.props.cell ? (<span>{this.props.cell.displayValue}</span>) : null;

        return (
            <div className={className}
                onDoubleClick={(e) => this.onDoubleClick(e)}
                onClick={(e) => this.onClick(e)}
                onKeyDown={(e) => this.onCellKeyDown(e)}
                style={{ width: this.props.size + 'px' }} >
                {value}
                {input}
            </div>
        );
    }

    componentDidMount()
    {
        if (this.props.editing)
            this.refs.input.focus();
    }

    componentDidUpdate()
    {
        if (this.props.editing)
            this.refs.input.focus();
    }

    componentWillReceiveProps(nextProps)
    {
        // Save cell value changes.
        if (this.props.editing && !nextProps.editing)
        {
            // TODO: Dispatch UI event.
        }
    }

    onDoubleClick(event)
    {
        store.dispatch({
            type: ActionType.SET_EDITING_CELL,
            id: this.props.sheet,
            cell: [ this.props.x, this.props.y ]
        });
    }

    onClick(event)
    {
        if (this.props.editing)
            return;

        store.dispatch({
            type: ActionType.SET_EDITING_CELL,
            id: this.props.sheet,
            cell: null
        });
    }

    onCellKeyDown(event)
    {

    }

    onValueChange(event)
    {
        this.setState({
            inputSize: !event.target.value.length ? 1 : (event.target.value.length * 1.1)
        });
    }

    onInputKeyDown(event)
    {
        // ESC pressed; discard changes.
        if (event.keyCode == 27)
        {
            event.target.value = this.props.cell ? this.props.cell.value : '';

            this.setState({
                inputSize: !event.target.value.length ? 1 : (event.target.value.length * 1.1)
            });

            store.dispatch({
                type: ActionType.SET_EDITING_CELL,
                id: this.props.sheet,
                cell: null
            });

            return;
        }

        // Enter pressed; persist changes.
        if (event.keyCode == 13)
        {
            // TODO: Dispatch UI event.

            store.dispatch({
                type: ActionType.SET_EDITING_CELL,
                id: this.props.sheet,
                cell: null
            });
        }
    }
}
