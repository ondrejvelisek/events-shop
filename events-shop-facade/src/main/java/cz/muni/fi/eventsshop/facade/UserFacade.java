package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Category;
import cz.muni.fi.eventsshop.model.User;

import java.util.List;

/**
 * Is responsible for Transactions.
 */
public interface UserFacade {

	User createUser(User user) throws InternalException;

	List<User> getAllUsers() throws InternalException;

	User getUserById(long id) throws InternalException;

	User getUserByOAuthId(String externalId) throws InternalException;

	void updateUser(long id, User data) throws InternalException;

}
