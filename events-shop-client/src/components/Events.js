import React, { Component } from 'react';
import {connect} from "react-redux";
import loader from "../images/loader.gif";
import {Link} from "react-router";
import {activateEvent} from "../actions/events";
import CreateEvent from "./CreateEvent";

class Events extends Component {

	activateEvent(eventId) {
		this.props.dispatch(activateEvent(eventId));
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
							 className={'list-group-item ' + (active === event.id ? 'active' : '')}>
							<h4 className="list-group-item-heading">{event.name}</h4>
							<p className="list-group-item-text">
								TODO
							</p>
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
