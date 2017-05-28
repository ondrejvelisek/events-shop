import Api from './Api'




class ServicesApi extends Api {

	getAllServices() {
		return this.call("/services").then(services =>
			services.map(service => {
				const categoryId = service.category.id;
				delete service.category;
				return {...service, categoryId};
			})
		);
	}

}

export default ServicesApi;
