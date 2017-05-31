package cz.muni.fi.eventsshop.model;

import cz.muni.fi.eventsshop.EventsShopTestDeployment;
import cz.muni.fi.eventsshop.repository.EventRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.rules.ExpectedException;

@RunWith(Arquillian.class)
public class EventRepositoryTest {

    @Inject
    private EventRepository eventRepository;

    @PersistenceContext
    private EntityManager manager;

    private Event event;
    private Event event2;
    private User client;
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Deployment
    public static WebArchive deployment() {
        return EventsShopTestDeployment.deployment();
    }  

    @Before
    public void init() {
        User client = new User();
        client.setName("Pan");
        client.setOAuthId("OAUTH");
        client.setEmail("my@email.cz");
        manager.persist(client);      
        
        event = new Event();
        event.setName("MyEvent");
        event.setClient(client);
        event.setDate(new Date(2017, 10, 20));
        
        event2 = new Event();
        event2.setName("MyEvent2");
        event2.setClient(client);
        event2.setDate(new Date(2017, 12, 2));
    } 
    
    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testCreate() {
        eventRepository.create(event);
        Assert.assertEquals(event, eventRepository.find(event.getId()));
    }
    
//    @Test(expected = javax.persistence.PersistenceException.class)
//    @Transactional(TransactionMode.DISABLED)
//    public void testDoubleCreate() {
//        //thrown.expect(PersistenceException.class);
//        eventRepository.create(event);
//        eventRepository.create(event);
//    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testNullCreate() {
        event = null;
        eventRepository.create(event);
    }
    
    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testNoNameCreate() {
        thrown.expect(ConstraintViolationException.class);
        event.setName("");
        eventRepository.create(event);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testNullNameCreate() {
        thrown.expect(ConstraintViolationException.class);
        event.setName(null);
        eventRepository.create(event);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testUpdate() {
        eventRepository.create(event);
        event.setName("other");
        eventRepository.update(event);
        Assert.assertEquals(event, eventRepository.find(event.getId()));

    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testNullUpdate() {
        //eventRepository.create(event);
        event = null;
        eventRepository.update(event);
    }
    
    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testNoNameUpdate() {                    
        thrown.expect(ConstraintViolationException.class);            
        event.setName("");
        eventRepository.update(event);
    }
    
    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testNullNameUpdate() {
        thrown.expect(ConstraintViolationException.class);
        event.setName(null);
        eventRepository.update(event);
    }
    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testFind() {
        eventRepository.create(event);
        Assert.assertEquals(event, eventRepository.find(event.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testFindNull() {
        eventRepository.find(null);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testFindAll() {        
        eventRepository.create(event);
        eventRepository.create(event2);
        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        eventList.add(event2);
        Assert.assertEquals(eventList, eventRepository.findAll());

    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testDelete() {
        eventRepository.create(event);
        eventRepository.delete(event);
    }
    
    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testDeleteNull() {
        eventRepository.delete(null);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testDeleteAndFind() {
        eventRepository.create(event);
        eventRepository.delete(event);
        Assert.assertNull(eventRepository.find(event.getId()));
    }

}
