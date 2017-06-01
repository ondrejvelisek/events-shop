import React, { PropTypes } from 'react'
import {Field, reduxForm} from 'redux-form'
import { renderField, renderTextarea } from './utils';

const required = value => (value ? undefined : 'Required');
const isNumber = value => (isNaN(Number(value)) ? 'Not a number' : undefined);
const positiveNumber = value => (Number(value) > 0 ? undefined : 'Number must be bigger than 0');

const ServiceForm = props => {
    const {handleSubmit, pristine, reset, isValid, categories} = props;

    const renderSelectbox =({input, label, meta: {touched, error}}) => {
        const showError = touched && error;
        const groupClass = showError ? 'has-error form-group' : 'form-group';
        return (
            <div className={groupClass}>
                <label className="control-label">{label}</label>
                <div>
                    <select className="form-control" {...input}>
                        <option value="">-- not selected --</option>
                        {categories.map(c => <option value={c.id} key={c.id}>{c.name}</option>)}
                    </select>
                    {showError && <div className="help-block">{error}</div>}
                </div>
            </div>
        );
    };

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
            <Field name="price"
                   type="number"
                   component={renderField}
                   label="Price"
                   validate={[required, isNumber, positiveNumber]}/>
            <Field name="category"
                   component={renderSelectbox}
                   label="Category"
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

ServiceForm.propTypes = {
    handleSubmit: PropTypes.func,
    reset: PropTypes.func,
    categories: PropTypes.array,
    submitting: PropTypes.bool,
    isValid: PropTypes.bool,
    pristine: PropTypes.bool
};

ServiceForm.defaultProps = {
    categories: []
};

export default reduxForm({
    form: 'service' // a unique identifier for this form
})(ServiceForm)