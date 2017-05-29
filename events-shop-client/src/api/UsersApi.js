import Api from './Api'




class UsersApi extends Api {

	getUserMe() {
		return this.call("/users/me");
	}

}

export default UsersApi;
