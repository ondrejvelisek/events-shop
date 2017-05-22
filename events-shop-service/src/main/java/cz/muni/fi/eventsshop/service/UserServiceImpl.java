package cz.muni.fi.eventsshop.service;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.User;
import cz.muni.fi.eventsshop.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by ondrejvelisek on 22.5.17.
 */
@ApplicationScoped
public class UserServiceImpl implements UserService {

	@Inject
	private UserRepository userRepository;

	@Override
	public void createUser(User user) throws InternalException {
		userRepository.createUser(user);
	}

	@Override
	public List<User> getAllUsers() throws InternalException {
		return userRepository.getAllUsers();
	}

	@Override
	public User getUserById(long id) throws InternalException {
		return userRepository.getUserById(id);
	}

	@Override
	public User getUserByExternalId(String externalId) throws InternalException {
		return userRepository.getUserByExternalId(externalId);
	}

	@Override
	public void updateUser(User user) throws InternalException {
		userRepository.updateUser(user);
	}
}
