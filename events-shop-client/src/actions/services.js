
export const FETCH_SERVICES_START = "FETCH_SERVICES_START";
export const FETCH_SERVICES_ERROR = "FETCH_SERVICES_ERROR";
export const UPDATE_SERVICES = "UPDATE_SERVICES";

export const WS_VIEWING_SERVICE_JOIN = "ws/VIEWING_SERVICE_JOIN";
export const WS_VIEWING_SERVICE_LEAVE = "ws/VIEWING_SERVICE_LEAVE";
export const VIEWING_SERVICE_COUNT = "VIEWING_SERVICE_COUNT";

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
			.then(services => dispatch(updateServices(services)))
			.catch(e => dispatch(fetchServicesError(e)));
	};
}

