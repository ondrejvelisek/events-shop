import 'whatwg-fetch';

export const FETCH_CATEGORIES_START = "FETCH_CATEGORIES_START";
export const FETCH_CATEGORIES_ERROR = "FETCH_CATEGORIES_ERROR";
export const FETCH_CATEGORIES_SUCCESS = "FETCH_CATEGORIES_SUCCESS";

export const CREATE_CATEGORY_START = 'CREATE_CATEGORY_START';
export const CREATE_CATEGORY_ERROR = 'CREATE_CATEGORY_ERROR';
export const CREATE_CATEGORY_SUCCESS = 'CREATE_CATEGORY_SUCCESS';

export const UPDATE_CATEGORY_START = 'UPDATE_CATEGORY_START';
export const UPDATE_CATEGORY_ERROR = 'UPDATE_CATEGORY_ERROR';
export const UPDATE_CATEGORY_SUCCESS = 'UPDATE_CATEGORY_SUCCESS';

export const RESET_CATEGORY_FORM = 'RESET_CATEGORY_FORM';



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
function fetchCategoriesSuccess(categories) {
	return {
		type: FETCH_CATEGORIES_SUCCESS,
		categories
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



function createCategoryStart(category) {
	return {
		type: CREATE_CATEGORY_START,
		category
	};
}
function createCategoryError(error) {
	return {
		type: CREATE_CATEGORY_ERROR,
		error
	};
}
function createCategorySuccess(category) {
	return {
		type: CREATE_CATEGORY_SUCCESS,
		category
	};
}
export function createCategory() {
	return (dispatch, getState, { api }) => {
		const state = getState();
		const newCategory = state.form.category.values;
		createCategoryStart(newCategory);
		api.categoriesApi.createCategory(newCategory)
			.then(category => dispatch(createCategorySuccess(category)))
			.catch(error => dispatch(createCategoryError(error)))
	};
}



function updateCategoryStart(category) {
	return {
		type: UPDATE_CATEGORY_START,
		category
	};
}
function updateCategoryError(error) {
	return {
		type: UPDATE_CATEGORY_ERROR,
		error
	};
}
function updateCategorySuccess(category) {
	return {
		type: UPDATE_CATEGORY_SUCCESS,
		category
	};
}
export function updateCategory(id) {
    return (dispatch, getState, { api }) => {
        const state = getState();
        const category = state.form.category.values;
        category.id = id;
        updateCategoryStart(category);
        api.categoriesApi.updateCategory(id, category)
            .then(() => dispatch(updateCategorySuccess(category)))
			.catch(error => dispatch(updateCategoryError(error)))
    };
}



export function resetCategoryForm() {
	return { type: RESET_CATEGORY_FORM }
}



export function initApp() {
	return (dispatch) => {
		dispatch(fetchCategories());
	};
}
