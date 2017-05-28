import {
	FETCH_SERVICES_START, FETCH_SERVICES_ERROR, UPDATE_SERVICES, VIEWING_SERVICE_COUNT
} from '../actions/services'

const servicesReducer = (state = {services: [], updating: false, viewing: {}}, action) => {
	switch (action.type) {

		case FETCH_SERVICES_START: {
			return {...state, updating: true};
		}

		case FETCH_SERVICES_ERROR: {
			// TODO deal with it
			console.error(action.error);
			return {...state, updating: false};
		}

		case UPDATE_SERVICES: {
			return {...state, updating: false, services: action.services};
		}

		case VIEWING_SERVICE_COUNT: {
			return {...state, viewing: action.count};
		}

		default:
			return state;
	}
};

export default servicesReducer;