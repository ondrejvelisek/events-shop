import React, { PropTypes } from 'react'
import {Field, reduxForm} from 'redux-form'
import { renderField, renderTextarea } from './utils';

const required = value => (value ? undefined : 'Required');

const CategoryForm = props => {
    const {handleSubmit, pristine, reset, isValid} = props;
    return (
        <form onSubmit={handleSubmit}>
            <Field name="name"
                   type="text"
                   component={renderField}
                   label="Name"
                   validate={required}/>
            <Field name="description"
                   component={renderTextarea}
                   label="Description"
                   validate={required}/>
            <div>
                <button className="btn btn-primary" type="submit" disabled={!isValid}>Submit</button>
                <button className="btn btn-default" type="button" disabled={pristine} onClick={reset}>
                    Clear Values
                </button>
            </div>
        </form>
    )
};

CategoryForm.propTypes = {
    handleSubmit: PropTypes.func,
    reset: PropTypes.func,
    categories: PropTypes.array,
    isValid: PropTypes.bool,
    pristine: PropTypes.bool
};

CategoryForm.defaultProps = {
    categories: []
};

export default reduxForm({
    form: 'category' // a unique identifier for this form
})(CategoryForm)