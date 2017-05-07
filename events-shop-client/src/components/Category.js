import React, { Component } from 'react';
import {connect} from "react-redux";
import loader from "../images/loader.gif";

class Category extends Component {

	render() {
		const { updating, categories } = this.props.categoriesState;
		const id = this.props.params.id;

		const loaderImage = <img src={loader} style={{height: '28px'}} alt="loading"/>;

		return (
            <div className="Category">
				<h2>
					<span>{id in categories ? categories[id].name : null}</span>
					<span> {updating ? loaderImage : null} </span>
					<span>{(!(id in categories) && !updating) ? 'Unknown category selected' : null}</span>
				</h2>
				<p>
					{id in categories ? categories[id].description : null}
				</p>
			</div>
		);
	}
}

const mapStateToProps = ({categoriesState}) => {
	return {
		categoriesState
	}
};

export default connect(mapStateToProps)(Category)
