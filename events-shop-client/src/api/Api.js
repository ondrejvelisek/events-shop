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
		args.headers = Object.assign(headers, args.headers);
		return fetch(this.baseUrl + path, args)
			.then(this.checkError)
			.then(response => {
				if (response.status !== 204 && response.headers.get('Content-Type').includes('application/json')) {
					return response.json()
				}
			});
	}

	checkError(response) {
		if (response.ok) {
			return response;
		} else {
			if (response.headers.get('Content-Type').includes('application/json')) {
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
