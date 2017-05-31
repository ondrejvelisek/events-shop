import React, { Component, PropTypes } from 'react';
import {connect} from "react-redux";
import Form from './form/CategoryForm';
import { createCategory } from '../actions/categories';

class CategoryNew extends Component {
    render() {
        const {anyTouched, syncErrors} = this.props.form;
        return (
            <div>
                <h2>New Category</h2>
                <Form onSubmit={this.props.save} pristine={anyTouched} isValid={!syncErrors}/>
            </div>
        )
    }
}

CategoryNew.propTypes = {
    save: PropTypes.func,
    form: PropTypes.any
};

CategoryNew.defaultProps = {
  form: {}
};



const mapStateToProps = ({categories_state, services_state, form}) => {
    return {
        categories_state,
        services_state,
        form: form.category
    }
};

export default connect(mapStateToProps, {
    save: createCategory
})(CategoryNew)
