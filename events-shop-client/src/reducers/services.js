import {
	FETCH_SERVICES_START, FETCH_SERVICES_ERROR, UPDATE_SERVICES, VIEWING_SERVICE_COUNT,
	CREATE_SERVICE_START, CREATE_SERVICE_SUCCESS, CREATE_SERVICE_ERROR
} from '../actions/services'

const servicesReducer = (state = {services: [], updating: false, viewing: {}}, action) => {
	switch (action.type) {

        case CREATE_SERVICE_START:
            return {...state, updating: true};

        case CREATE_SERVICE_ERROR:
            // TODO deal with it
            console.error(action.error);
            return {...state, updating: false};

        case CREATE_SERVICE_SUCCESS:
            return {...state, updating: false, services: [...state.services, action.service]};

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