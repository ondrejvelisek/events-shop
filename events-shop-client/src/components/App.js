import React, { Component } from 'react';
import { Link } from 'react-router'
import { initApp } from "../actions/categories";
import { connect } from 'react-redux';
import { userManager } from '../userManager';

class App extends Component {

	componentWillMount() {
		this.props.dispatch(
			initApp()
		);
	}

	signin() {
		userManager.signinRedirect({ data: { redirectUrl: window.location.pathname }});
	}

	render() {

		let login;
		if (this.props.user) {
			login = (<p className="navbar-text navbar-right">{this.props.user.profile.name}</p>);
		} else {
			login = (
				<button className="btn btn-default navbar-btn navbar-right" onClick={this.signin.bind(this)}>
					Sign in
				</button>
			);
		}

		return (
			<div className="App">

				<div className="App-header">

					<div className="navbar navbar-default navbar-static-top">
						<div className="container">
							<div className="navbar-header">
								<Link to="/" className="navbar-brand">Events Shop</Link>
							</div>
							<ul className="nav navbar-nav">
								<li><Link to="/">Home</Link></li>
								<li><Link to="/categories">Categories</Link></li>
							</ul>
							<p className="navbar-text navbar-right">
								<i className="glyphicon glyphicon-shopping-cart"/> cart
							</p>
							{login}
						</div>
					</div>

				</div>

				<div className="container">
					<div className="row">
						<div className="col-sm-12">

							<div className="App-content">
								{this.props.children}
							</div>

						</div>
					</div>
				</div>


			</div>
		);
	}
}

const mapStateToProps = ({auth}) => {
	return {
		user: auth.user
	}
};

export default connect(mapStateToProps)(App)
