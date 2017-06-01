
export const FETCH_SERVICES_START = "FETCH_SERVICES_START";
export const FETCH_SERVICES_ERROR = "FETCH_SERVICES_ERROR";
export const UPDATE_SERVICES = "UPDATE_SERVICES";

export const WS_VIEWING_SERVICE_JOIN = "ws/VIEWING_SERVICE_JOIN";
export const WS_VIEWING_SERVICE_LEAVE = "ws/VIEWING_SERVICE_LEAVE";
export const VIEWING_SERVICE_COUNT = "VIEWING_SERVICE_COUNT";

export const CREATE_SERVICE_START = 'CREATE_SERVICE_START';
export const CREATE_SERVICE_ERROR = 'CREATE_SERVICE_ERROR';
export const CREATE_SERVICE_SUCCESS = 'CREATE_SERVICE_SUCCESS';

export function joinServicePage(serviceId) {
	return {
		type: WS_VIEWING_SERVICE_JOIN,
		serviceId: Number(serviceId)
	};
}

export function leaveServicePage(serviceId) {
	return {
		type: WS_VIEWING_SERVICE_LEAVE,
		serviceId: Number(serviceId)
	};
}

function fetchServicesStart() {
	return {
		type: FETCH_SERVICES_START
	};
}

function fetchServicesError(error) {
	return {
		type: FETCH_SERVICES_ERROR,
		error
	};
}

export function updateServices(services) {
	return {
		type: UPDATE_SERVICES,
		services
	};
}

export function fetchServices() {
	return (dispatch, getState, { api }) => {
		dispatch(fetchServicesStart());
		api.servicesApi.getAllServices()
			.then(services =>
				services.map(service => {
					const categoryId = service.category.id;
					delete service.category;
					return {...service, categoryId};
				})
			)
			.then(services => dispatch(updateServices(services)))
			.catch(e => dispatch(fetchServicesError(e)));
	};
}
function createServiceStart(service) {
    return {
        type: CREATE_SERVICE_START,
        service
    };
}
function createServiceError(error) {
    return {
        type: CREATE_SERVICE_ERROR,
        error
    };
}
function createServiceSuccess(service) {
    return {
        type: CREATE_SERVICE_SUCCESS,
        service
    };
}
export function createService() {
    return (dispatch, getState, { api }) => {
        const state = getState();
        const newService = state.form.service.values;
        newService.category = Number(newService.category);
        newService.price = Number(newService.price);
        createServiceStart(newService);
        api.servicesApi.createService(newService)
            .then(service => dispatch(createServiceSuccess(service)))
            .catch(error => dispatch(createServiceError(error)))
    };
}

export function deleteService(id) {
    return (dispatch, getState, { api }) => {
        api.servicesApi.deleteService(id);
        // .then(categories => dispatch(fetchCategoriesSuccess(categories)))
        // .catch(e => dispatch(fetchCategoriesError(e)));
    };
}


