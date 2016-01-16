import React, { Component, PropTypes } from 'react'
import { connect } from 'react-redux'

class BaseContainer extends Component
{
    render()
    {
        const { workbook } = this.props;

        return (
            <div className="app-view">

            </div>
        );
    }
}

const mapStateToProps = (state) => { return state; };

export default connect(
    mapStateToProps
)(BaseContainer);