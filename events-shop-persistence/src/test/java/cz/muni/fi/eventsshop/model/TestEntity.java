package cz.muni.fi.eventsshop.model;

import cz.muni.fi.eventsshop.EventsShopTestDeployment;
import cz.muni.fi.eventsshop.repository.CategoryRepository;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;
import org.junit.Test;

@RunWith(Arquillian.class)
public class TestEntity {

//	@Deployment
//	public static AddonArchive getDeployment() {
//		return ShrinkWrap.create(AddonArchive.class).addBeansXML();
//	}
    @Inject
    CategoryRepository categoryRepository;

//    @Deployment
//    public static WebArchive deployment() {
//        return EventsShopTestDeployment.deployment();
//    }

    
    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "tests.war")
                .addPackages(true, "cz.muni.fi.eventsshop")
                .addAsResource("META-INF/persistence.xml");
    }

    @Test
    public void test() {
        Category cat = new Category();
        cat.setName("cars");
        cat.setDescription("long description");
        categoryRepository.create(cat);

    }
}
