import {
	FETCH_CATEGORIES_ERROR, FETCH_CATEGORIES_START, UPDATE_CATEGORIES
} from '../actions/categories'

const categoriesReducer = (state = {categories: [], updating: false}, action) => {
	switch (action.type) {

		case FETCH_CATEGORIES_START:
			return {...state, updating: true};

		case FETCH_CATEGORIES_ERROR:
			// TODO deal with it
			console.error(action.error);
			return {...state, updating: false};

		case UPDATE_CATEGORIES:
			return {...state, updating: false, categories: action.categories};

		default:
			return state;
	}
};

export default categoriesReducer;