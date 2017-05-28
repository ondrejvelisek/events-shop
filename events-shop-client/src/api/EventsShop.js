import 'whatwg-fetch'
import CategoriesApi from "./CategoriesApi";
import ServicesApi from "./ServicesApi";

class EventsShop {

	constructor(userManager, baseUrl) {
		this.categoriesApi = new CategoriesApi(userManager, baseUrl);
		this.servicesApi = new ServicesApi(userManager, baseUrl);
	}

}

export default EventsShop;
