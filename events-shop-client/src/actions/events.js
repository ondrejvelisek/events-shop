
export const FETCH_EVENTS_START = "FETCH_EVENTS_START";
export const FETCH_EVENTS_ERROR = "FETCH_EVENTS_ERROR";
export const UPDATE_EVENTS = "UPDATE_EVENTS";

export const ACTIVATE_EVENT = "ACTIVATE_EVENT";

export const CREATE_EVENT_START = "CREATE_EVENT_START";
export const CREATE_EVENT_ERROR = "CREATE_EVENT_ERROR";
export const CREATE_EVENT_SUCCESS = "CREATE_EVENT_SUCCESS";

export const DELETE_EVENT_START = "DELETE_EVENT_START";
export const DELETE_EVENT_ERROR = "DELETE_EVENT_ERROR";
export const DELETE_EVENT_SUCCESS = "DELETE_EVENT_SUCCESS";

function fetchEventsStart() {
	return {
		type: FETCH_EVENTS_START
	};
}

function fetchEventsError(error) {
	return {
		type: FETCH_EVENTS_ERROR,
		error
	};
}

export function updateEvents(events) {
	return {
		type: UPDATE_EVENTS,
		events
	};
}

export function activateEvent(eventId) {
	return {
		type: ACTIVATE_EVENT,
		eventId
	};
}

export function fetchEvents() {
	return (dispatch, getState, { api }) => {
		dispatch(fetchEventsStart());
		api.eventsApi.getMyEvents()
			.then(events => dispatch(updateEvents(events)))
			.catch(e => dispatch(fetchEventsError(e)));
	};
}

function createEventStart() {
	return {
		type: CREATE_EVENT_START
	};
}

function createEventError(error) {
	return {
		type: CREATE_EVENT_ERROR,
		error
	};
}

function createEventSuccess(event) {
	return {
		type: CREATE_EVENT_SUCCESS,
		event
	};
}

export function createEvent(event) {
	return (dispatch, getState, { api }) => {
		dispatch(createEventStart(event));
		api.usersApi.getUserMe()
			.then((user) => {
				event = {...event,
					eventServices: [],
					state: 'NEW',
					client: user
				};
				return api.eventsApi.createEvent(event)
					.then(event => dispatch(createEventSuccess(event)))
					.catch(e => dispatch(createEventError(e)))
			});
	};
}


function deleteEventStart(eventId) {
	return {
		type: DELETE_EVENT_START,
		eventId
	};
}

function deleteEventError(error) {
	return {
		type: DELETE_EVENT_ERROR,
		error
	};
}

function deleteEventSuccess(eventId) {
	return {
		type: DELETE_EVENT_SUCCESS,
		eventId
	};
}

export function deleteEvent(eventId) {
	return (dispatch, getState, { api }) => {
		dispatch(deleteEventStart(eventId));
		return api.eventsApi.deleteEvent(eventId)
			.then(() => dispatch(deleteEventSuccess(eventId)))
			.catch(e => dispatch(deleteEventError(e)))
	};
}

