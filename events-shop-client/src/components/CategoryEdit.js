import React, { Component, PropTypes } from 'react';
import {connect} from "react-redux";
import Form from './form/CategoryForm';
import { updateCategory } from '../actions/categories';

class CategoryEdit extends Component {
    render() {
        return (
            <div>
                <h2>Editing Category</h2>
                <Form handleSubmit={this.props.save}/>
            </div>
        )
	}
}

CategoryEdit.propTypes = {
    save: PropTypes.func,
    form: PropTypes.any
};



function mapStateToProps(state) {
    console.log(state);
    return null;
}

export default connect(mapStateToProps, {
    save: updateCategory
})(CategoryEdit)
