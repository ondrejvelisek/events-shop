package cz.muni.fi.eventsshop.service.impl;

import cz.muni.fi.eventsshop.model.User;
import cz.muni.fi.eventsshop.repository.UserRepository;
import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.service.UserService;

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
		userRepository.create(user);
	}

	@Override
	public List<User> getAllUsers() throws InternalException {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(long id) throws InternalException {
		return userRepository.find(id);
	}

	@Override
	public User getUserByOAuthId(String externalId) throws InternalException {
		return userRepository.findByOAuthId(externalId);
	}

	@Override
	public void updateUser(User user) throws InternalException {
		userRepository.update(user);
	}
}
