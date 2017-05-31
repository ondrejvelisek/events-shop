import 'whatwg-fetch';

export const FETCH_CATEGORIES_START = "FETCH_CATEGORIES_START";
export const FETCH_CATEGORIES_ERROR = "FETCH_CATEGORIES_ERROR";
export const UPDATE_CATEGORIES = "UPDATE_CATEGORIES";
export const CREATE_CATEGORY = 'CREATE_CATEGORY';
export const UPDATE_CATEGORY = 'UPDATE_CATEGORY';
export const RESET_CATEGORY_FORM = 'RESET_CATEGORY_FORM';


function fetchCategoriesStart() {
	return {
		type: FETCH_CATEGORIES_START
	};
}

export function createCategory() {
	//todo ani za picu sa tu v tych API komponentach nevyznam
	//skratka chcem zavolat POST na CATEGORY_URL s BODY `newCategory`
    return (dispatch, getState, { api }) => {
    	const state = getState();
    	const newCategory = state.form.category;
        dispatch(fetchCategoriesStart());
        api.categoriesApi.getAllCategories()
            .then(categories => dispatch(updateCategories(categories)))
            .catch(e => dispatch(fetchCategoriesError(e)));
    };
}

export function updateCategory(id) {
    //todo ani za picu sa tu v tych API komponentach nevyznam
    //skratka chcem zavolat PUT na CATEGORY_URL s BODY `newCategory`
    return (dispatch, getState, { api }) => {
        const state = getState();
        const category = state.form.category;
        category.id = id;
        dispatch(fetchCategoriesStart());
        api.categoriesApi.getAllCategories()
            .then(categories => dispatch(updateCategories(categories)))
            .catch(e => dispatch(fetchCategoriesError(e)));
    };
}

export function resetCategoryForm() {
	return { type: RESET_CATEGORY_FORM }
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
