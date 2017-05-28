package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.exceptions.BeanNotExistsException;
import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.User;
import cz.muni.fi.eventsshop.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@ApplicationScoped
public class UserFacadeImpl implements UserFacade {

	@Inject
	private UserService service;

	@Override
	public User createUser(User user) throws InternalException {
		service.createUser(user);
		return service.getUserById(user.getId());
	}

	@Override
	public List<User> getAllUsers() throws InternalException {
		return service.getAllUsers();
	}

	@Override
	public User getUserById(long id) throws InternalException {
		return service.getUserById(id);
	}

	@Override
	public User getUserByExternalId(String externalId) throws InternalException {
		return service.getUserByExternalId(externalId);
	}

	@Override
	public void updateUser(long id, User data) throws InternalException {
		if (service.getUserById(id) == null){
			throw new BeanNotExistsException("User with id "+ id + " does not exist.");
		}
		service.updateUser(data);
	}

}
