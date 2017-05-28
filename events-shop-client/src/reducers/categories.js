import {
	FETCH_CATEGORIES_ERROR, FETCH_CATEGORIES_START, FETCH_CATEGORIES_SUCCESS,
	WS_COUNT
} from '../actions/categories'

const categoriesReducer = (state = {categories: {}, updating: false, viewing: {}}, action) => {
	switch (action.type) {

		case FETCH_CATEGORIES_START:
			return {...state, updating: true};

		case FETCH_CATEGORIES_SUCCESS:
			let categories = {};
			action.categories.forEach((category) => {categories[category.id] = category});
			return {...state, updating: false, categories};

		case WS_COUNT:
			return {...state, viewing: action.count};

		case FETCH_CATEGORIES_ERROR:
			// TODO deal with it
			console.error(action.error);
			return {...state, updating: false};

		default:
			return state;
	}
};

export default categoriesReducer;