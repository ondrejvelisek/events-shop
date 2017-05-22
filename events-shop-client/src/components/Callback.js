import React, { Component } from 'react';
import { CallbackComponent } from 'redux-oidc';
import { push } from 'react-router-redux';
import { connect } from 'react-redux';

class Callback extends Component {

	successCallback = (user) => {
		const urlBeforeRedirection = user.state.redirectUrl;
		this.props.dispatch(push(urlBeforeRedirection));
	};

	errorCallback = (error) => {
		console.log(error.message);
	};

	render() {
		return <CallbackComponent successCallback={this.successCallback} errorCallback={this.errorCallback} />;
	}
}

export default connect()(Callback);
