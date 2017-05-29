import 'whatwg-fetch'
import CategoriesApi from "./CategoriesApi";
import ServicesApi from "./ServicesApi";
import EventsApi from "./EventsApi";
import UsersApi from "./UsersApi";

class EventsShop {

	constructor(userManager, baseUrl) {
		this.categoriesApi = new CategoriesApi(userManager, baseUrl);
		this.servicesApi = new ServicesApi(userManager, baseUrl);
		this.eventsApi = new EventsApi(userManager, baseUrl);
		this.usersApi = new UsersApi(userManager, baseUrl);
	}

}

export default EventsShop;
