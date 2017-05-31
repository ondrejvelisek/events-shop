package cz.muni.fi.eventsshop.repository;

import cz.muni.fi.eventsshop.model.User;

import java.util.List;

/**
 * Created by peter on 5/26/17.
 */
public interface UserRepository {

    User create(User user);

    User update(User user);

    User findByOAuthId(String id);

    User find(Long userId);

    List<User> findAll();

    void delete(User user);
}
