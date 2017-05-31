import React, { Component } from 'react';
import {connect} from "react-redux";
import loader from "../images/loader.gif";
import {activateEvent, deleteEvent} from "../actions/events";
import CreateEvent from "./CreateEvent";
import {Link} from "react-router";

class Events extends Component {

	activateEvent(eventId) {
		this.props.dispatch(activateEvent(eventId));
	}

	deleteEvent(e, eventId) {
		e.stopPropagation();
		this.props.dispatch(deleteEvent(eventId));
	}

	orderEvent(eventId) {
		this.props.dispatch({type: "ORDER_EVENT"});
	}

	render() {
		const { updating, events, active } = this.props.events_state;
		const activeEvent = events.find(event => event.id === active);

		const loaderImage = <img src={loader} style={{height: '28px'}} alt="loading"/>;

		return (
            <div className="events">

				<div className="list-group">
					{Object.values(events).map(event =>
						<a href="#" key={event.id}
							 onClick={this.activateEvent.bind(this, event.id)}
							 className={'clearfix list-group-item ' + (active === event.id ? 'active' : '')}>
							<h4 className="clearfix list-group-item-heading">
								{event.name}
								<div className="pull-right btn-group">
									<Link className="btn btn-primary" to={'/categories'}>
										<i className="glyphicon glyphicon-arrow-right"/> Continue shopping
									</Link>
									<button className="btn btn-danger" onClick={(e) => this.deleteEvent(e, event.id)}>
										<i className="glyphicon glyphicon-remove"/>
									</button>
								</div>
							</h4>
							<p className="list-group-item-text">
								TODO: list of EventService
							</p>
							<hr/>
							<p className="list-group-item-text">
								TODO: total price
							</p>
							<button className="pull-right btn btn-success" onClick={(e) => this.orderEvent(event)}>
								Send order <i className="glyphicon glyphicon-ok"/>
							</button>
						</a>
					)}
					<div className='list-group-item'>
						<CreateEvent/>
					</div>
				</div>

			</div>
		);
	}
}

const mapStateToProps = ({events_state}) => {
	return {
		events_state
	}
};

export default connect(mapStateToProps)(Events)
