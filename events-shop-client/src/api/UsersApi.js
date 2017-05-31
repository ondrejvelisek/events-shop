import Api from './Api'




class UsersApi extends Api {

	getUserMe() {
		return this.call("/users/me");
	}

	getAllUsers() {
		return this.call("/users");
	}

}

export default UsersApi;
