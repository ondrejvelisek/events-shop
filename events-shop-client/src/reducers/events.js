import {
	FETCH_EVENTS_ERROR, FETCH_EVENTS_START, UPDATE_EVENTS,
	ACTIVATE_EVENT,
	CREATE_EVENT_START, CREATE_EVENT_ERROR, CREATE_EVENT_SUCCESS
} from '../actions/events'

const eventsReducer = (state = {events: [], updating: false}, action) => {
	switch (action.type) {

		case FETCH_EVENTS_START:
			return {...state, updating: true};

		case FETCH_EVENTS_ERROR:
			// TODO deal with it
			console.error(action.error);
			return {...state, updating: false};

		case UPDATE_EVENTS:
			return {...state, updating: false, events: action.events};


		case ACTIVATE_EVENT:
			return {...state, active: action.eventId};


		case CREATE_EVENT_START:
			return {...state, updating: true};

		case CREATE_EVENT_ERROR:
			// TODO deal with it
			console.error(action.error);
			return {...state, updating: false};

		case CREATE_EVENT_SUCCESS:
			return {...state, updating: false, events: [...state.events, action.event]};

		default:
			return state;
	}
};

export default eventsReducer;