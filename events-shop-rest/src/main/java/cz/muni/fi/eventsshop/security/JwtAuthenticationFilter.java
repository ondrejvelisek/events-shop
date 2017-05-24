package cz.muni.fi.eventsshop.security;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.facade.UserFacade;
import cz.muni.fi.eventsshop.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;

/**
 * Filter verify JWT token obtained in Authorization header against configured public key.
 * It tries to find user in system. If is not present it creates one based on claims in JWT token.
 */
@Priority(Priorities.AUTHENTICATION)
public class JwtAuthenticationFilter implements ContainerRequestFilter {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	public static final String AUTH_SCHEME = "JWT";

	private final String PROP_FILE = "conf.properties";
	private final String KEYS_URL_PROP = "jwt.keys.url";
	private final String ISSUER_PROP = "jwt.issuer";
	private final String AUDIENCE_PROP = "jwt.audience";

	@Context
	private ResourceInfo resourceInfo;

	private UserFacade userFacade;

	public JwtAuthenticationFilter(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

	@Override
	public void filter(ContainerRequestContext containerRequestContext) throws IOException {

		String headerValue = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		if (headerValue == null || headerValue.isEmpty()) {
			log.debug("Authorization header is not present or is empty");
			throw new ForbiddenException();
		}

		String type = headerValue.split(" ", 2)[0];

		if (!type.toLowerCase().trim().equals("bearer")) {
			log.debug("Authorization header is not type Bearer");
			throw new ForbiddenException();
		}

		String token = headerValue.split(" ", 2)[1].trim();

		log.trace("Token extracted from Request: "+token);

		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		try(InputStream resourceStream = loader.getResourceAsStream(PROP_FILE)) {
			Properties conf = new Properties();
			conf.load(resourceStream);

			DecodedJWT jwt = JWT.decode(token);

			JwkProvider provider = new UrlJwkProvider(new URL(conf.getProperty(KEYS_URL_PROP)));
			Jwk jwk = provider.get(jwt.getKeyId());

			log.trace("Signing provider key found and parsed: "+jwk.toString());

			Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
			JWTVerifier verifier = JWT.require(algorithm)
					.withIssuer(conf.getProperty(ISSUER_PROP))
					.acceptLeeway(5)
					.withAudience(conf.getProperty(AUDIENCE_PROP))
					.build();
			verifier.verify(token);

			log.trace("JWT verified successfully.");

			log.warn("HERE: " + userFacade);

			JwtPrincipal principal = new JwtPrincipal(jwt);
			User user = userFacade.getUserByExternalId(principal.getName());

			if (user == null) {
				log.trace("User for principal "+principal+" has not been found. " +
						"Creating new one.");
				user = userFacade.createUser(new User(
						principal.getName(),
						new HashSet<>(Arrays.asList("USER")),
						principal.getJwt().getClaim("name").asString(),
						principal.getJwt().getClaim("email").asString()
				));
				log.trace("User has been created " + user);
			} else {
				log.trace("User for principal "+principal+" has been found "+user);
				log.trace("Going to update info of user "+user+" based on principal "+principal);
				user.adjust(new User(
						user.getExternalId(),
						user.getRoles(),
						principal.getJwt().getClaim("name").asString(),
						principal.getJwt().getClaim("email").asString()
				));
				userFacade.updateUser(user.getId(), user);
				log.trace("Info of user "+user+" has been updated.");
			}

			containerRequestContext.setSecurityContext(new AppSecurityContext(AUTH_SCHEME, user, principal));

			log.trace("User "+user+" has been authenticated. " +
					"Security context has been set with principal" +
					containerRequestContext.getSecurityContext().getUserPrincipal()
			);

		} catch (InternalException e) {
			throw new InternalServerErrorException(e);
		} catch (JwkException e) {
			throw new InternalServerErrorException(e);
		} catch (JWTVerificationException e) {
			throw new ForbiddenException(e);
		}

	}
}
