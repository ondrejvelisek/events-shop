package cz.muni.fi.eventsshop.repository.impl;


import cz.muni.fi.eventsshop.model.User;
import cz.muni.fi.eventsshop.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by peter on 5/26/17.
 */
@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public User create(User user) {
        manager.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        manager.merge(user);
        return user;
    }

    @Override
    public User find(Long userId) {
        return manager.find(User.class, userId);
    }

    @Override
    public List<User> findAll() {
        return manager.createQuery("from " + User.class.getName(), User.class).getResultList();
    }

    @Override
    public void delete(User user) {
        manager.remove(user);
    }
}
