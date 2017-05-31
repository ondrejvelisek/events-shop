import Api from './Api'




class EventsApi extends Api {

	createEvent(event) {
		return this.call("/events", {
			method: 'POST',
			headers: {
				'Content-Type': "application/json; charset=UTF-8"
			},
			body: JSON.stringify(event)
		});
	}

	getMyEvents() {
		return this.call("/events");
	}

	getEventById(eventId) {
		return this.call("/events/"+eventId);
	}

	updateEvent(id, event) {
		return this.call("/events/"+id, {
			method: 'PUT',
			headers: {
				'Content-Type': "application/json; charset=UTF-8"
			},
			body: JSON.stringify(event)
		})
	}

	deleteEvent(eventId) {
		return this.call("/events/"+eventId, {
			method: 'DELETE'
		});
	}

}

export default EventsApi;
