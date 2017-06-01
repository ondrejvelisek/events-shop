import React, { Component, PropTypes } from 'react';
import {connect} from "react-redux";
import Form from './form/ServiceForm';
import { createService } from '../actions/services';

class ServiceNew extends Component {
    render() {
        const {anyTouched, syncErrors} = this.props.form;
        return (
            <div>
                <h2>New Service</h2>
                <Form categories={this.props.categories} onSubmit={this.props.save} pristine={anyTouched} isValid={!syncErrors}/>
            </div>
        )
    }
}

ServiceNew.propTypes = {
    save: PropTypes.func,
    form: PropTypes.any,
    categories: PropTypes.array
};

ServiceNew.defaultProps = {
  form: {}
};

const mapStateToProps = ({categories_state, form}) => {
    return {
        categories: categories_state.categories,
        form: form.service
    }
};

export default connect(mapStateToProps, {
    save: createService
})(ServiceNew)
