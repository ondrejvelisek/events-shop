
export const FETCH_CATEGORIES_START = "FETCH_CATEGORIES_START";
export const FETCH_CATEGORIES_SUCCESS = "FETCH_CATEGORIES_SUCCESS";
export const FETCH_CATEGORIES_ERROR = "FETCH_CATEGORIES_ERROR";
export const WS_JOIN = "ws/VIEWING_SERVICE_JOIN";
export const WS_LEAVE = "ws/VIEWING_SERVICE_LEAVE";
export const WS_COUNT = "VIEWING_SERVICE_COUNT";


export function joinServicePage(serviceId) {
	return {
		type: WS_JOIN,
		serviceId: parseInt(serviceId)
	};
}

export function leaveServicePage(serviceId) {
	return {
		type: WS_LEAVE,
		serviceId: parseInt(serviceId)
	};
}

function fetchCategoriesStart() {
	return {
		type: FETCH_CATEGORIES_START
	};
}

function fetchCategoriesSuccess(categories) {
	return {
		type: FETCH_CATEGORIES_SUCCESS,
		categories
	};
}

function fetchCategoriesError(error) {
	return {
		type: FETCH_CATEGORIES_ERROR,
		error
	};
}

export function fetchCategories() {
	return (dispatch, getState, { api }) => {
		dispatch(fetchCategoriesStart());
		api.categoriesApi.getAllCategories()
			.then(categories => dispatch(fetchCategoriesSuccess(categories)))
			.catch(e => dispatch(fetchCategoriesError(e)));
	};
}


export function initApp() {
	return (dispatch) => {
		dispatch(fetchCategories());
	};
}
