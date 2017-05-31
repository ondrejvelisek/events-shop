import React, { Component, PropTypes } from 'react';
import {connect} from "react-redux";
import Form from './form/CategoryForm';
import { updateCategory, fetchCategory } from '../actions/categories';

class CategoryEdit extends Component {
    componentDidMount() {
        this.props.fetch(this.props.id);
    }

    render() {
        const {anyTouched, syncErrors} = this.props.form;
        return (
            <div>
                <h2>Edit Category</h2>
                <Form onSubmit={this.props.save} pristine={anyTouched} isValid={!syncErrors}/>
            </div>
        )
    }
}

CategoryEdit.propTypes = {
    save: PropTypes.func,
    fetch: PropTypes.func,
    form: PropTypes.any
};

CategoryEdit.defaultProps = {
    form: {}
};


const mapStateToProps = ({categories_state, services_state, form}, {params}) => {
    return {
        categories_state,
        services_state,
        form: form.category,
        id: Number(params.id)
    }
};

export default connect(mapStateToProps, {
    save: updateCategory,
    fetch: fetchCategory
})(CategoryEdit)
