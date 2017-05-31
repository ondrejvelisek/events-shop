import Api from './Api'




class ServicesApi extends Api {

	createService(service) {
		return this.call("/services", {
			method: 'POST',
			headers: {
				'Content-Type': "application/json; charset=UTF-8"
			},
			body: JSON.stringify(service)
		})
	}

	getAllServices() {
		return this.call("/services");
	}

	getServiceById(serviceId) {
		return this.call("/services/"+serviceId);
	}

	updateService(id, service) {
		return this.call("/services/"+id, {
			method: 'PUT',
			headers: {
				'Content-Type': "application/json; charset=UTF-8"
			},
			body: JSON.stringify(service)
		})
	}

	deleteService(serviceId) {
		return this.call("/services/"+serviceId, {
			method: 'DELETE',
		})
	}

}

export default ServicesApi;
