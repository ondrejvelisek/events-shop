import React, { Component } from 'react';
import { connect } from 'react-redux';
import { LinkContainer } from 'react-router-bootstrap';
import { NavItem } from "react-bootstrap";

class Categories extends Component {
	render() {
		let { categories } = this.props.categories_state;
		const title = <h2>Please select one of the categories</h2>;
		return (
            <div className="Categories">
				<div className="row">
					<div className="col-sm-3">

						<ul className="nav nav-pills nav-stacked">
							<li className="text-muted">
								Categories
							</li>

							<div className="nav-divider"/>
                            {categories.map(category =>
                                <LinkContainer key={category.id} to={`/categories/${category.id}`}>
                                    <NavItem>{category.name}</NavItem>
                                </LinkContainer>
                            )}
						</ul>
					</div>
					<div className="col-sm-9">
						{this.props.children || title}
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
