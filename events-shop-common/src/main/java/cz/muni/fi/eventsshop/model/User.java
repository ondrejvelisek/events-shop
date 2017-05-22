package cz.muni.fi.eventsshop.model;

import java.util.Set;

/**
 *
 */
public class User extends Bean implements Adjustable<User> {

	private String externalId;
	private Set<String> roles;
	private String name;
	private String email;

	public User() {
		// JavaEE JSON parser needs default constructor
	}

	public User(String externalId, Set<String> roles, String name, String email) {
		this.externalId = externalId;
		this.roles = roles;
		this.name = name;
		this.email = email;
	}

	public String getExternalId() {
		return externalId;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public void adjust(User bean) {
		this.externalId = bean.getExternalId();
		this.roles = bean.getRoles();
		this.name = bean.getName();
		this.email = bean.getEmail();
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", externalId='" + externalId + '\'' +
				", roles=" + roles +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
