import 'whatwg-fetch'

class Api {

	constructor(userManager, baseUrl) {
		this.userManager = userManager;
		this.baseUrl = baseUrl;
	}

	call(path, args = {}) {
		return this.userManager.getUser().then(user => this.callWithUser(user, path, args));
	}

	callWithUser(user, path, args = {}) {
		let headers = {};
		if (user && user.id_token) {
			headers['Authorization'] = 'Bearer '+user.id_token;
		}
		return fetch(this.baseUrl + path, Object.assign({ headers }, args))
			.then(this.checkError)
			.then(response => response.json());
	}

	checkError(response) {
		if (response.ok) {
			return response;
		} else {
			if (response.headers.get('Content-Type') === 'application/json') {
				return response.json().then(json => {
					throw new Error(json.message);
				});
			} else {
				throw new Error(response.statusText);
			}
		}
	}

}

export default Api;
