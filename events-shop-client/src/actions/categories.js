
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
