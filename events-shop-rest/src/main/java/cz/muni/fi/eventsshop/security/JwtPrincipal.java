package cz.muni.fi.eventsshop.security;

import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.Principal;

/**
 *
 */
public class JwtPrincipal implements Principal {

	private DecodedJWT jwt;

	public JwtPrincipal(DecodedJWT jwt) {
		this.jwt = jwt;
	}

	@Override
	public String getName() {
		return jwt.getSubject();
	}

	public DecodedJWT getJwt() {
		return jwt;
	}

	@Override
	public String toString() {
		return "JwtPrincipal{" +
				"name='" + getName() + '\'' +
				'}';
	}
}
