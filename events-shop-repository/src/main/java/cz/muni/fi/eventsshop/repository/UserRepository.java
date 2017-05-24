package cz.muni.fi.eventsshop.repository;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.User;

import java.util.List;

/**
 * Repository is responsible for persisting and getting beans from data storage.
 */
public interface UserRepository {

	void createUser(User user) throws InternalException;

	List<User> getAllUsers() throws InternalException;

	User getUserById(long id) throws InternalException;

	User getUserByExternalId(String externalId) throws InternalException;

	void updateUser(User user) throws InternalException;

}
