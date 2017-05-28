import React, { Component } from 'react';
import {connect} from "react-redux";
import loader from "../images/loader.gif";
import { joinServicePage, leaveServicePage } from "../actions/categories";

class Category extends Component {

	componentDidMount() {
		this.props.dispatch(joinServicePage(this.props.params.id))
	}

	componentWillReceiveProps(nextProps) {
		if (this.props.params.id !== nextProps.params.id) {
			this.props.dispatch(joinServicePage(nextProps.params.id));
		}
	}

	componentWillUnmount() {
		this.props.dispatch(leaveServicePage(this.props.params.id))
	}

	render() {
		const { updating, categories, viewing } = this.props.categoriesState;
		const id = this.props.params.id;

		const viewingCount = viewing[id] ? viewing[id] : 0;

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
				<p>
					This category is being viewed by {viewingCount} people
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
