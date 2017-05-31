/**
 * Created by peter on 5/30/17.
 */
import React from 'react';

export function renderField({input, label, type, meta: {touched, error}}) {
    const showError = touched && error;
    const groupClass = showError ? 'has-error form-group' : 'form-group';
    return (
        <div className={groupClass}>
            <label className="control-label">{label}</label>
            <div>
                <input className="form-control" {...input} placeholder={label} type={type} />
                {showError && <div className="help-block">{error}</div>}
            </div>
        </div>
    );
}

export function renderTextarea({input, label, meta: {touched, error}}) {
    const showError = touched && error;
    const groupClass = showError ? 'has-error form-group' : 'form-group';
    return (
        <div className={groupClass}>
            <label className="control-label">{label}</label>
            <div>
                <textarea className="form-control" {...input} placeholder={label} />
                {showError && <div className="help-block">{error}</div>}
            </div>
        </div>
    );
}