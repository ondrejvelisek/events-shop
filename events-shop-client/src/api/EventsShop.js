import 'whatwg-fetch'
import CategoriesApi from "./CategoriesApi";

class EventsShop {

	constructor(userManager, baseUrl) {
		this.categoriesApi = new CategoriesApi(userManager, baseUrl);
	}

}

export default EventsShop;
