import Api from './Api'




class ServicesApi extends Api {

	getAllServices() {
		return this.call("/services");
	}

}

export default ServicesApi;
