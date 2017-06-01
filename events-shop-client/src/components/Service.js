import React, { Component } from 'react';
import {connect} from "react-redux";
import loader from "../images/loader.gif";
import { joinServicePage, leaveServicePage, deleteService } from "../actions/services";
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
                        <div className="pull-right">
                            <Link to="/services/new">
                                <span className="btn btn-success">
                                    <span className="glyphicon glyphicon-plus"/>
                                </span>
                            </Link>
                            <Link to={`/services/${service.id}/edit`}>
                                <span className="btn btn-primary">
                                    <span className="glyphicon glyphicon-pencil"/>
                                </span>
                            </Link>
							<Link to={`/categories`} onClick={() => this.props.deleteService(id)}>
								<span className="btn btn-sm btn-danger">
									<span className="glyphicon glyphicon-trash"/>
								</span>
							</Link>

						</div>
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

		const loaderImage = <img src={loader} style={{height: '28px'}} alt="loading"/>;
	}
}

const mapStateToProps = ({services_state, categories_state}) => {
	return {
		services_state,
		categories_state
	}
};

export default connect(mapStateToProps, {
    deleteService
})(Service)
