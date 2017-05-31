package cz.muni.fi.eventsshop.model;

import cz.muni.fi.eventsshop.EventsShopTestDeployment;
import cz.muni.fi.eventsshop.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;

@RunWith(Arquillian.class)
public class UserRepositoryTest {

    @Inject
    private UserRepository userRepository;

    private User user;
    private User user2;

    @Deployment
    public static WebArchive deployment() {
        return EventsShopTestDeployment.deployment();
    }

    @Before
    public void init() {

        user = new User();
        user.setName("Pan");
        user.setOAuthId("OAUTH");
        user.setEmail("my@email.cz");

        user2 = new User();
        user2.setName("Pan2");
        user2.setOAuthId("OAUTH2");
        user2.setEmail("my2@email.cz");

    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testCreate() {
        userRepository.create(user);
        Assert.assertEquals(user, userRepository.find(user.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testNullCreate() {
        user = null;
        userRepository.create(user);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testUpdate() {
        userRepository.create(user);
        user.setEmail("dummy@dummy.cz");
        userRepository.update(user);
        Assert.assertEquals(user, userRepository.find(user.getId()));

    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testNullUpdate() {
        user = null;
        userRepository.update(user);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testFind() {
        userRepository.create(user);
        Assert.assertEquals(user, userRepository.find(user.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testFindNull() {
        userRepository.find(null);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testFindAll() {
        userRepository.create(user);
        userRepository.create(user2);
        List<User> eventList = new ArrayList<>();
        eventList.add(user);
        eventList.add(user2);
        Assert.assertEquals(eventList, userRepository.findAll());

    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testDelete() {
        userRepository.create(user);
        userRepository.delete(user);
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testDeleteNull() {
        userRepository.delete(null);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testDeleteAndFind() {
        userRepository.create(user);
        userRepository.delete(user);
        Assert.assertNull(userRepository.find(user.getId()));
    }

}
