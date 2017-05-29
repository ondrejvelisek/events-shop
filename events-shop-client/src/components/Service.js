import React, { Component } from 'react';
import {connect} from "react-redux";
import loader from "../images/loader.gif";
import { joinServicePage, leaveServicePage } from "../actions/services";
import {Link} from "react-router";

class Service extends Component {

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
		const { updating, services, viewing } = this.props.services_state;
		const id = Number(this.props.params.id);
		const service = services.find(service => service.id === id);
		const viewingService = viewing[id] ? viewing[id] : 0;
		const category = service ? this.props.categories_state.categories.find(category => category.id === service.categoryId) : null;

		const loaderImage = <img src={loader} style={{height: '28px'}} alt="loading"/>;

		return (
            <div className="service">
				{category ? (
					<p>
						<Link to={'/categories/'+category.id}>{category.name}</Link>
					</p>
				) : null}
				<h2>
					<span>{service ? service.name : null}</span>
					<span> {updating ? loaderImage : null} </span>
					<span>{(!(service) && !updating) ? 'Unknown service selected' : null}</span>
				</h2>
				{service ? (
					<div>
						<p>
							{service.description}
						</p>
						<p>
							This category is being viewed by {viewingService} people
						</p>
					</div>
				) : null}
			</div>
		);
	}
}

const mapStateToProps = ({services_state, categories_state}) => {
	return {
		services_state,
		categories_state
	}
};

export default connect(mapStateToProps)(Service)
