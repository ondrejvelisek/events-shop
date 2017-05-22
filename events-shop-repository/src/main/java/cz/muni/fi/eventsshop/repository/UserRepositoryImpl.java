package cz.muni.fi.eventsshop.repository;

import cz.muni.fi.eventsshop.exceptions.BeanAlreadyExistsException;
import cz.muni.fi.eventsshop.exceptions.BeanNotExistsException;
import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Stupid implementation using in-memory Map. No transaction or concurrency support.
 */
@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

	private Map<Long, User> users;

	@PostConstruct
	private void init() throws InternalException {
		users = new HashMap<>();
		createUser(new User(
				"superuser",
				new HashSet<>(Arrays.asList("USER", "ADMIN")),
				"Super User",
				"superuser@mail.com"
		));
		createUser(new User(
				"user",
				new HashSet<>(Arrays.asList("USER")),
				"Ordinal User",
				"ordinaluser@mail.com"
		));
		createUser(new User(
				"105202389642828188450",
				new HashSet<>(Arrays.asList("USER", "ADMIN")),
				"Ondra Velisek",
				"ondravelisek@seznam.cz"
		));
	}

	@Override
	public void createUser(User user) throws InternalException {
		if (users.containsKey(user.getId())) {
			throw new BeanAlreadyExistsException("User with id "+user.getId()+" already exists.");
		}
		if (containsExternalId(users.values(), user.getExternalId())) {
			throw new BeanAlreadyExistsException("User with external id "+user.getExternalId()+" already exists.");
		}
		Set<Long> ids = users.keySet();
		long lastId;
		try {
			lastId = Collections.max(ids);
		} catch (NoSuchElementException e) {
			lastId = 0;
		}
		users.put(lastId + 1, user);
		user.setId(lastId + 1);
	}

	@Override
	public List<User> getAllUsers() throws InternalException {
		return new ArrayList<>(users.values());
	}

	@Override
	public User getUserById(long id) throws InternalException {
		if (!users.containsKey(id)) {
			throw new BeanNotExistsException("User with id "+id+" was not found.");
		}
		return users.get(id);
	}

	@Override
	public User getUserByExternalId(String externalId) throws InternalException {
		if (!containsExternalId(users.values(), externalId)) {
			return null;
		}
		return users.values().stream().filter(u -> u.getExternalId().equals(externalId)).findAny().get();
	}

	@Override
	public void updateUser(User user) throws InternalException {
		if (!users.containsKey(user.getId())) {
			throw new BeanNotExistsException("Updating user "+user+" was not found.");
		}
		users.put(user.getId(), user);
	}

	private boolean containsExternalId(Collection<User> users, String externalId) {
		return users.stream().anyMatch(u -> u.getExternalId().equals(externalId));
	}
}
