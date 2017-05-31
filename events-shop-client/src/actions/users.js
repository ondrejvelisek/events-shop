
export const FETCH_USER_ME_START = "FETCH_USER_ME_START";
export const FETCH_USER_ME_ERROR = "FETCH_USER_ME_ERROR";
export const FETCH_USER_ME_SUCCESS = "FETCH_USER_ME_SUCCESS";
export const USER_EXPIRED = "redux-oidc/USER_EXPIRED";

function fetchUserMeStart() {
	return {
		type: FETCH_USER_ME_START
	};
}

function fetchUserMeError(error) {
	return {
		type: FETCH_USER_ME_ERROR,
		error
	};
}

export function fetchUserMeSuccess(user) {
	return {
		type: FETCH_USER_ME_SUCCESS,
		user: user
	};
}

export function fetchUserMe() {
	return (dispatch, getState, { api }) => {
		dispatch(fetchUserMeStart());
		api.usersApi.getUserMe()
			.then(user => dispatch(fetchUserMeSuccess(user)))
			.catch(e => dispatch(fetchUserMeError(e)));
	};
}

