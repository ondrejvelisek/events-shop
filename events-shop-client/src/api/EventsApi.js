import Api from './Api'




class EventsApi extends Api {

	getMyEvents() {
		return this.call("/events");
	}

	createEvent(event) {
		return this.call("/events", {
			method: 'POST',
			headers: {
				'Content-Type': "application/json; charset=UTF-8"
			},
			body: JSON.stringify(event)
		});
	}

	deleteEvent(eventId) {
		return this.call("/events/"+eventId, {
			method: 'DELETE'
		});
	}

}

export default EventsApi;
