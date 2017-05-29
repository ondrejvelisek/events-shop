
export const FETCH_CATEGORIES_START = "FETCH_CATEGORIES_START";
export const FETCH_CATEGORIES_ERROR = "FETCH_CATEGORIES_ERROR";
export const UPDATE_CATEGORIES = "UPDATE_CATEGORIES";


function fetchCategoriesStart() {
	return {
		type: FETCH_CATEGORIES_START
	};
}

function fetchCategoriesError(error) {
	return {
		type: FETCH_CATEGORIES_ERROR,
		error
	};
}

export function updateCategories(categories) {
	return {
		type: UPDATE_CATEGORIES,
		categories
	};
}

export function fetchCategories() {
	return (dispatch, getState, { api }) => {
		dispatch(fetchCategoriesStart());
		api.categoriesApi.getAllCategories()
			.then(categories => dispatch(updateCategories(categories)))
			.catch(e => dispatch(fetchCategoriesError(e)));
	};
}


export function initApp() {
	return (dispatch) => {
		dispatch(fetchCategories());
	};
}
