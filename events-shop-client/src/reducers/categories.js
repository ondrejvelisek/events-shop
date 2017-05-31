import {
	FETCH_CATEGORIES_START, FETCH_CATEGORIES_ERROR, FETCH_CATEGORIES_SUCCESS,
	CREATE_CATEGORY_START, CREATE_CATEGORY_ERROR, CREATE_CATEGORY_SUCCESS,
	UPDATE_CATEGORY_START, UPDATE_CATEGORY_ERROR, UPDATE_CATEGORY_SUCCESS,
	FETCH_CATEGORY_SUCCESS
} from '../actions/categories'

const categoriesReducer = (state = {categories: [], updating: false}, action) => {
	switch (action.type) {

		case CREATE_CATEGORY_START:
			return {...state, updating: true};

		case CREATE_CATEGORY_ERROR:
			// TODO deal with it
			console.error(action.error);
			return {...state, updating: false};

		case CREATE_CATEGORY_SUCCESS:
			return {...state, updating: false, categories: [...state.categories, action.category]};


		case UPDATE_CATEGORY_START:
			return {...state, updating: true};

		case UPDATE_CATEGORY_ERROR:
			// TODO deal with it
			console.error(action.error);
			return {...state, updating: false};

		case UPDATE_CATEGORY_SUCCESS: {
			const categories = state.categories.map(category => {
				if (category.id === action.category.id) {
					return action.category;
				} else {
					return category;
				}
			});
			return {...state, updating: false, categories};
		}


		case FETCH_CATEGORIES_START:
			return {...state, updating: true};

		case FETCH_CATEGORIES_ERROR:
			// TODO deal with it
			console.error(action.error);
			return {...state, updating: false};

		case FETCH_CATEGORIES_SUCCESS:
			return {...state, updating: false, categories: action.categories};

		case FETCH_CATEGORY_SUCCESS:
			return {...state, updating: false, category: action.category};

		default:
			return state;
	}
};

export default categoriesReducer;