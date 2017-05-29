import {createUserManager} from "redux-oidc";



const config = {
	client_id: '635048093758-3fqf32dtfgaathcacij15fuosjivphfq.apps.googleusercontent.com',
	redirect_uri: `${window.location.protocol}//${window.location.hostname}:${window.location.port}/callback`,
	response_type: 'id_token token',
	scope: 'openid profile email',
	authority: 'https://accounts.google.com',
	automaticSilentRenew: false,
	filterProtocolClaims: true,
	loadUserInfo: false
};

export const userManager = createUserManager(config);