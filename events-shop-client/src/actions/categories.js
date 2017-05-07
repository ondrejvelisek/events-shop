import 'whatwg-fetch'

export const FETCH_CATEGORIES_START = "FETCH_CATEGORIES_START";
export const FETCH_CATEGORIES_SUCCESS = "FETCH_CATEGORIES_SUCCESS";
export const FETCH_CATEGORIES_ERROR = "FETCH_CATEGORIES_ERROR";

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
	return (dispatch) => {
		dispatch(fetchCategoriesStart());
		fetch("http://localhost:8080/events-shop-rest/api/v0.1/categories")
			.then(checkError)
			.then(response => response.json())
			.then(json => dispatch(fetchCategoriesSuccess(json)))
			.catch(e => dispatch(fetchCategoriesError(e)))
	};
}


export function initApp() {
	return (dispatch) => {
		dispatch(fetchCategories());
	};
}

function checkError(response) {
	if (response.ok) {
		return response;
	} else {
		if (response.headers.get('Content-Type') === 'application/json') {
			return response.json().then(json => {
				throw new Error(json.message);
			});
		} else {
			throw new Error(response.statusText);
		}
	}
}