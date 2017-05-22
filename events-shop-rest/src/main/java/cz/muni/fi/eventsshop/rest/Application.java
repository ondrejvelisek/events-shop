package cz.muni.fi.eventsshop.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.ApplicationPath;

/**
 *
 */
@ApplicationScoped
@ApplicationPath("api/v0.1")
public class Application extends javax.ws.rs.core.Application {
}
