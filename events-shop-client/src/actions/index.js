import {fetchCategories} from "./categories";
import {fetchServices} from "./services";


export function initApp() {
	return (dispatch) => {
		dispatch(fetchCategories());
		dispatch(fetchServices());
	};
}
