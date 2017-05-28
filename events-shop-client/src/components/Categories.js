import React, { Component } from 'react';
import { connect } from 'react-redux';
import NavItem from "./NavItem";

class Categories extends Component {

	render() {

		let { categories } = this.props.categories_state;

		return (
            <div className="Categories">
				<div className="row">
					<div className="col-sm-3">

						<ul className="nav nav-pills nav-stacked">

							<li className="text-muted">
								Categories
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

const mapStateToProps = ({categories_state}) => {
	return {
		categories_state
	}
};

export default connect(mapStateToProps)(Categories)
