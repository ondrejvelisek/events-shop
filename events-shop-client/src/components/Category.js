import React, { Component } from 'react';
import {connect} from "react-redux";
import loader from "../images/loader.gif";
import {Link} from "react-router";
import Form from './form/EventForm';

class Category extends Component {

	render() {
		const { updating, categories } = this.props.categories_state;
		const id = Number(this.props.params.id);
		const category = categories.find(category => category.id === id);
		const services = this.props.services_state.services.filter(service => service.categoryId === id);

		const loaderImage = <img src={loader} style={{height: '28px'}} alt="loading"/>;

		const topBnts = category ?
            <div className="pull-right">
                <Link to="/categories/new">
                    <span className="btn btn-success">
                        <span className="glyphicon glyphicon-plus"/>
                    </span>
                </Link>
                <Link to={`/categories/${category.id}/edit`}>
                    <span className="btn btn-primary">
                        <span className="glyphicon glyphicon-pencil"/>
                    </span>
                </Link>
            </div> : null;

        return (
            <div className="category">
                {topBnts}
				<h2>
					<span>{category ? category.name : null}</span>
					<span> {updating ? loaderImage : null} </span>
					<span>{(!(category) && !updating) ? 'Unknown category selected' : null}</span>
				</h2>
				<p>
					{category ? category.description : null}
				</p>

				<div className="row">
					{Object.values(services).map(service =>
						<div key={service.id} className="col-sm-6 col-md-4">
							<Link to={'/services/'+service.id} className="thumbnail">
								<div className="caption">
									<h3>{service.name}</h3>
									<p>{service.description}</p>
								</div>
							</Link>
						</div>
					)}
				</div>
				<Form services={[{id: 1, name: 'cool', desc: 'naprd'}, {id: 2, name: 'cool2', desc: 'naprd2'}]}/>
			</div>
		);
	}
}

const mapStateToProps = ({categories_state, services_state}) => {
	return {
		categories_state,
		services_state
	}
};

export default connect(mapStateToProps)(Category)
