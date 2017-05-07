import React, { Component } from 'react';
import { Link } from 'react-router'
import { initApp } from "../actions/categories";
import { connect } from 'react-redux';


class App extends Component {

	componentWillMount() {
		this.props.dispatch(
			initApp()
		);
	}

	render() {
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
								<span className="glyphicon glyphicon-shopping-cart"/> cart
							</p>
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


export default connect()(App)
