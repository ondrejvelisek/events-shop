import React, { Component } from 'react';
import { Link } from 'react-router'
import { initApp } from "../actions";
import { connect } from 'react-redux';
import { userManager } from '../utils/userManager';
import { NavDropdown, MenuItem } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';
import {activateEvent} from "../actions/events";

class App extends Component {

	componentWillMount() {
		this.props.dispatch(
			initApp()
		);
	}

	signin() {
		userManager.signinRedirect({ data: { redirectUrl: window.location.pathname }});
	}

	activateEvent(eventId) {
		this.props.dispatch(activateEvent(eventId));
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

		const { updating, events, active } = this.props.events_state;
		const activeEvent = events.find(event => event.id === active);

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
							<ul className="nav navbar-nav navbar-right">
								<NavDropdown title={activeEvent ? activeEvent.name : 'cart'} id="cartMenu">

									{Object.values(events).map(event =>
										<MenuItem key={event.id}
												  onClick={this.activateEvent.bind(this, event.id)}
												  className={active === event.id ? 'active' : ''}>
											{event.name}
										</MenuItem>
									)}

									<MenuItem divider />
									<LinkContainer to={{ pathname: '/events'}}>
										<MenuItem><i className="glyphicon glyphicon-plus"/> Manage</MenuItem>
									</LinkContainer>
								</NavDropdown>
							</ul>
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

const mapStateToProps = ({auth_state, events_state}) => {
	return {
		user: auth_state.user,
		events_state
	}
};

export default connect(mapStateToProps)(App)
