package cz.muni.fi.eventsshop.model;

import cz.muni.fi.eventsshop.EventsShopTestDeployment;
import cz.muni.fi.eventsshop.repository.EventServiceRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class EventServiceRepositoryTest {

    @Inject
    private EventServiceRepository eventServiceRepository;

    @PersistenceContext
    private EntityManager manager;

    private EventService eventService;
    private EventService eventService2;
       
    @Deployment
    public static WebArchive deployment() {
        return EventsShopTestDeployment.deployment();
    }  

    @Before
    public void init() {
        
        Category category = new Category();
        category.setName("cars");
        category.setDescription("long description");
        manager.persist(category);
                        
        Service service = new Service();
        service.setName("serviceName");
        service.setDescription("something");
        service.setPrice(BigDecimal.ONE);
        service.setCategory(category);
        manager.persist(service);
        
        
        eventService = new EventService();
        eventService.setCount(1);
        eventService.setPrice(BigDecimal.ONE);
        eventService.setService(service);
        
        eventService2 = new EventService();
        eventService2.setCount(2);
        eventService2.setPrice(BigDecimal.TEN);
        eventService2.setService(service);
        
    } 
    
    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testCreate() {
        eventServiceRepository.create(eventService);
        Assert.assertEquals(eventService, eventServiceRepository.find(eventService.getId()));
    }
    
    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testNullCreate() {
        eventService = null;
        eventServiceRepository.create(eventService);
    }      

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testUpdate() {
        eventServiceRepository.create(eventService);
        eventService.setPrice(BigDecimal.TEN);
        eventServiceRepository.update(eventService);
        Assert.assertEquals(eventService, eventServiceRepository.find(eventService.getId()));

    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testNullUpdate() {
        eventService = null;
        eventServiceRepository.update(eventService);
    }        
    
    
    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testFind() {
        eventServiceRepository.create(eventService);
        Assert.assertEquals(eventService, eventServiceRepository.find(eventService.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testFindNull() {
        eventServiceRepository.find(null);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testFindAll() {        
        eventServiceRepository.create(eventService);
        eventServiceRepository.create(eventService2);
        List<EventService> eventList = new ArrayList<>();
        eventList.add(eventService);
        eventList.add(eventService2);
        Assert.assertEquals(eventList, eventServiceRepository.findAll());

    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testDelete() {
        eventServiceRepository.create(eventService);
        eventServiceRepository.delete(eventService);
    }
    
    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testDeleteNull() {
        eventServiceRepository.delete(null);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testDeleteAndFind() {
        eventServiceRepository.create(eventService);
        eventServiceRepository.delete(eventService);
        Assert.assertNull(eventServiceRepository.find(eventService.getId()));
    }

}
