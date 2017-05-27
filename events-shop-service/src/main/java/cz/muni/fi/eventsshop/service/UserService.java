package cz.muni.fi.eventsshop.service;

import cz.muni.fi.eventsshop.model.User;
import cz.muni.fi.eventsshop.exceptions.InternalException;

import java.util.List;

/**
 * Service is responsible for bussiness logic
 */
public interface UserService {

	void createUser(User user) throws InternalException;

	List<User> getAllUsers() throws InternalException;

	User getUserById(long id) throws InternalException;

	User getUserByExternalId(String externalId) throws InternalException;

	void updateUser(User user) throws InternalException;

}
