import React, { Component, PropTypes } from 'react';
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
                    <span className="btn btn-sm btn-success">
                        <span className="glyphicon glyphicon-plus"/>
                    </span>
                </Link>
                <Link to={`/categories/${category.id}/edit`}>
                    <span className="btn btn-sm btn-primary">
                        <span className="glyphicon glyphicon-pencil"/>
                    </span>
                </Link>
                <span className="btn btn-sm btn-danger">
                    <span className="glyphicon glyphicon-trash"/>
                </span>
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
                    <table className="table table-hover">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Operations</th>
                        </tr>
                        </thead>
                        <tbody>
                        {services.map(service =>
                        <tr key={service.id}>
                            <td>
                                <Link to={`/services/${service.id}`}>{service.name}</Link>
                            </td>
                            <td>{service.description}</td>
                            <td>
                                <Link to={`/services/${service.id}/edit`}>
                                    <span className="btn btn-xs btn-primary">
                                        <span className="glyphicon glyphicon-pencil"/>
                                    </span>
                                </Link>
                                <button className="btn btn-xs btn-danger" type="button" onClick={() => console.log('DELETE')}>
                                    <span className="glyphicon glyphicon-trash" />
                                </button>
                            </td>
                        </tr>
                        )}
                        </tbody>
                    </table>
				</div>
				<Form onSubmit={() => console.log('coool')} services={[{id: 1, name: 'cool', desc: 'naprd'}, {id: 2, name: 'cool2', desc: 'naprd2'}]}/>
			</div>
		);
	}
}

Category.propTypes = {
    services: PropTypes.array,
    category: PropTypes.any
};

Category.defaultProps = {
    services: [],
    category: {}
};


const mapStateToProps = ({categories_state, services_state}) => {
	return {
		categories_state,
		services_state
	}
};

export default connect(mapStateToProps)(Category)
