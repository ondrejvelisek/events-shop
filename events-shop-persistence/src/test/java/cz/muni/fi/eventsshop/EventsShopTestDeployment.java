package cz.muni.fi.eventsshop;
//import org.jboss.examples.ticketmonster.util.Resources;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class EventsShopTestDeployment {

    public static WebArchive deployment() {
        return ShrinkWrap
                .create(WebArchive.class, "test.war")
                .addPackages(true, "cz.muni.fi.eventsshop")
                .addAsResource("META-INF/test-persistence.xml","META-INF/persistence.xml")
                //.addAsResource("import.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
