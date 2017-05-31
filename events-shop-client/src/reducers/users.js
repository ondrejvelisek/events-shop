import {
	FETCH_USER_ME_START, FETCH_USER_ME_ERROR, FETCH_USER_ME_SUCCESS, USER_EXPIRED
} from '../actions/users'

const servicesReducer = (state = {me: null, updating: false}, action) => {
	switch (action.type) {

		case FETCH_USER_ME_START: {
			return {...state, updating: true};
		}

		case FETCH_USER_ME_ERROR: {
			return {...state, updating: false, me: null};
		}

		case FETCH_USER_ME_SUCCESS: {
			return {...state, updating: false, me: action.user};
		}

		case USER_EXPIRED: {
			return {...state, me: null};
		}

		default:
			return state;
	}
};

export default servicesReducer;