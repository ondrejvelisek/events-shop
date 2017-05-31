import {fetchCategories} from "./categories";
import {fetchServices} from "./services";
import {fetchUserMe} from "./users";
import {fetchEvents} from "./events";


export function initApp() {
	return (dispatch) => {
		dispatch(fetchCategories());
		dispatch(fetchServices());
		dispatch(fetchEvents());
		dispatch(fetchEvents());
	};
}
