import React, { Component } from 'react';
import {connect} from "react-redux";
import {ControlLabel, FormControl, FormGroup, HelpBlock} from "react-bootstrap";
import {createEvent} from "../actions/events";

class CreateEvent extends Component {

	constructor(props) {
		super(props);
		this.initialState = {name: '', date: '2017-08-01'};
		this.state = this.initialState;
	}

	nameValidationState() {
		const length = this.state.name.length;
		if (length >= 1 && length < 3) return 'error';
		else if (length >= 3) return 'success';
		else return null;
	}

	handleChange(e) {
		this.setState({ name: e.target.value });
	}

	createNewEvent(e) {
		e.preventDefault();
		if (this.props.events_state.updating === false) {
			this.props.dispatch(createEvent({
				name: this.state.name,
				date: this.state.date
			}));
			this.resetForm();
		}
	}

	resetForm() {
		this.state = this.initialState;
	}

	render() {

		const { updating, events, active } = this.props.events_state;

		return (
			<div className="create-event">

				<h4 className="list-group-item-heading">Create new event</h4>

				<form onSubmit={this.createNewEvent.bind(this)} disabled={updating}>
					<FormGroup controlId="name"
							   validationState={this.nameValidationState()}>
						<ControlLabel>
							Name
						</ControlLabel>
						<FormControl type="text"
									 value={this.state.name}
									 placeholder="type name of your new event ..."
									 onChange={this.handleChange.bind(this)}
						/>
						<HelpBlock>
							Name has to have at least 3 characters
						</HelpBlock>
					</FormGroup>
				</form>

			</div>
		);
	}
}

const mapStateToProps = ({events_state}) => {
	return {
		events_state
	}
};

export default connect(mapStateToProps)(CreateEvent)
