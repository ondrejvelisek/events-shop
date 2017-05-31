import React, { PropTypes } from 'react'
import {Field, FieldArray, reduxForm} from 'redux-form'
import { renderField } from './utils';

const required = value => (value ? undefined : 'Required');
const isNumber = value => (isNaN(Number(value)) ? 'Not a number' : undefined);
const positiveNumber = value => (Number(value) > 0 ? undefined : 'Number must be bigger than 0');
const date = value => (new Date(value).toString() === 'Invalid Date' ? 'Not a date': undefined);
const afterToday = value => (new Date(value) <= Date.now() ? 'Cannot book sooner date than tomorrow': undefined);
const requiredArr = value => (value && value.length ? undefined : 'Required');

const EventForm = props => {
    const {handleSubmit, pristine, reset, submitting, isValid, services} = props;

    const renderSelectbox =({input, label, meta: {touched, error}}) => {
        const showError = touched && error;
        const groupClass = showError ? 'has-error form-group' : 'form-group';
        return (
            <div className={groupClass}>
                <label className="control-label">{label}</label>
                <div>
                    <select className="form-control" {...input}>
                        {services.map(s  => <option value={s.id} key={s.id}>{s.name}</option>)}
                    </select>
                    {showError && <div className="help-block">{error}</div>}
                </div>
            </div>
        );
    };

    const renderServices = ({fields, meta: {error, submitFailed}}) => (
        <ul>
            <li>
                <button type="button" onClick={() => fields.push({})}>Add Service</button>
                {submitFailed && error && <span>{error}</span>}
            </li>
            {fields.map((service, index) => (
                <li key={index}>
                    <button type="button" title="Remove Service" onClick={() => fields.remove(index)}>&times;</button>
                    <h4>Service #{index + 1}</h4>
                    <Field name={`${service}.category`}
                           component={renderSelectbox}
                           options={[{id: 1, name: 'a'}, {id: 2, name: 'b'}]}
                           label="Category"
                           validate={required}/>
                    <Field name={`${service}.count`}
                           type="number"
                           component={renderField}
                           label="Count"
                           validate={[required, isNumber, positiveNumber]}/>
                </li>
            ))}
        </ul>
    );

    return (
        <form onSubmit={handleSubmit}>
            <Field name="name"
                   type="text"
                   component={renderField}
                   label="Name"
                   validate={required}/>
            <Field name="city"
                   type="text"
                   component={renderField}
                   label="City"
                   validate={required}/>
            <Field name="address"
                   type="text"
                   component={renderField}
                   label="Address"
                   validate={required}/>
            <Field name="date"
                   type="date"
                   component={renderField}
                   label="Date"
                   validate={[required, date, afterToday]}/>
            <FieldArray name="services"
                        component={renderServices}
            />
            <div>
                <button type="submit" >Submit</button>
                <button type="button" disabled={pristine || submitting} onClick={reset}>
                    Clear Values
                </button>
            </div>
        </form>
    )
};

EventForm.propTypes = {
    handleSubmit: PropTypes.func,
    reset: PropTypes.func,
    submitting: PropTypes.bool,
    isValid: PropTypes.bool,
    pristine: PropTypes.bool
};

export default reduxForm({
    form: 'event' // a unique identifier for this form
})(EventForm)