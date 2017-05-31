package cz.muni.fi.eventsshop.model;

import cz.muni.fi.eventsshop.EventsShopTestDeployment;
import cz.muni.fi.eventsshop.repository.ServiceRepository;
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
public class ServiceRepositoryTest {

    @Inject
    private ServiceRepository serviceRepository;

    @PersistenceContext
    private EntityManager manager;

    private Service service;
    private Service service2;       

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
                        
        service = new Service();
        service.setName("serviceName");
        service.setDescription("something");
        service.setPrice(BigDecimal.ONE);
        service.setCategory(category);
        
        service2 = new Service();
        service2.setName("serviceName");
        service2.setDescription("something");
        service2.setPrice(BigDecimal.ONE);
        service2.setCategory(category);
                
    } 
    
    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testCreate() {
        serviceRepository.create(service);
        Assert.assertEquals(service, serviceRepository.find(service.getId()));
    }   

    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testNullCreate() {
        service = null;
        serviceRepository.create(service);
    }       

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testUpdate() {
        serviceRepository.create(service);
        service.setPrice(BigDecimal.TEN);
        serviceRepository.update(service);
        Assert.assertEquals(service, serviceRepository.find(service.getId()));

    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testNullUpdate() {
        service = null;
        serviceRepository.update(service);
    }        
    
    
    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testFind() {
        serviceRepository.create(service);
        Assert.assertEquals(service, serviceRepository.find(service.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testFindNull() {
        serviceRepository.find(null);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testFindAll() {        
        serviceRepository.create(service);
        serviceRepository.create(service2);
        List<Service> eventList = new ArrayList<>();
        eventList.add(service);
        eventList.add(service2);
        Assert.assertEquals(eventList, serviceRepository.findAll());

    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testDelete() {
        serviceRepository.create(service);
        serviceRepository.delete(service);
    }
    
    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testDeleteNull() {
        serviceRepository.delete(null);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testDeleteAndFind() {
        serviceRepository.create(service);
        serviceRepository.delete(service);
        Assert.assertNull(serviceRepository.find(service.getId()));
    }

}
