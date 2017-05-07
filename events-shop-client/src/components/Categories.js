import React, { Component } from 'react';
import { connect } from 'react-redux';
import {fetchCategories} from "../actions/categories";
import NavItem from "./NavItem";

class Categories extends Component {

	componentWillMount() {
		this.refresh();
	}

	refresh = () => {
		this.props.dispatch(
			fetchCategories()
		);
	};

	render() {

		let { categories, updating } = this.props.categoriesState;

		return (
            <div className="Categories">
				<div className="row">
					<div className="col-sm-3">

						<ul className="nav nav-pills nav-stacked">

							<li className="text-muted">
								Categories
								<button onClick={this.refresh} className="btn-link">
									<i className="glyphicon glyphicon-refresh" style={updating ? {animation: 'spin 2s infinite linear'} : {}}/>
								</button>
							</li>

							<div className="nav-divider"/>
							{Object.values(categories).map(category =>
								<NavItem key={category.id} to={"/categories/"+category.id} activeClassName="active">
									{category.name}
								</NavItem>
							)}

						</ul>

					</div>
					<div className="col-sm-9">

						{this.props.children}

					</div>
				</div>
            </div>
		);
	}
}

const mapStateToProps = ({categoriesState}) => {
	return {
		categoriesState
	}
};

export default connect(mapStateToProps)(Categories)
