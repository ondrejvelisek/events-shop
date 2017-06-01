/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rest;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Category;
import cz.muni.fi.eventsshop.repository.CategoryRepository;
import cz.muni.fi.eventsshop.repository.impl.CategoryRepositoryImpl;
import cz.muni.fi.eventsshop.rest.CategoryRest;
import org.apache.http.HttpStatus;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.extension.rest.app.Customer;
import org.jboss.arquillian.extension.rest.app.CustomerResource;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.extension.rest.client.ClassModifier;
import org.jboss.arquillian.extension.rest.client.Header;
import org.jboss.arquillian.extension.rest.client.Headers;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.util.List;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.as.controller.access.constraint.Constraint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;

@RunWith(Arquillian.class)
public class CategoryRestTestCase {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @ArquillianResource
    private URL deploymentURL;

    private final String restResource = "api/v0.1";
    
    private Category testCategory;

    @Deployment(testable = true)
    public static WebArchive create() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "cz.muni.fi.eventsshop")
                .addPackages(true, "com.auth0.jwk")
                .addPackages(true, "com.auth0.jwt")
                .addAsResource("META-INF/persistence.xml");
    }
    
    @BeforeClass
    public static void staticInit(){
//        testCategory = new Category();
//        testCategory.setName("TestCategory");
//        testCategory.setDescription("TestCategoryDescription");        
    }
    

    @Before
    public void init() {
        //System.out.println("***TEST DEPLOYMENT:"+deploymentURL);
        testCategory = new Category();
        testCategory.setName("TestCategory");
        testCategory.setDescription("TestCategoryDescription");
        //testCategory.setId(100l);
        
    }
    
    public Category getCategoryByName(CategoryRest categoryRest, String name) throws InternalException{        
        Object[] cat = categoryRest.getAllCategories().stream()
                .filter(x -> x.getName().equals(name)).toArray();
        Category foundCategory = cat.length > 0 ? (Category) cat[0] : new Category();
        return foundCategory;
    }
    
    

    @Test
    public void getAllCategories(
            @ArquillianResteasyResource(restResource) CategoryRest categoryRest) throws InternalException {

        System.out.println("***FOUND CATEGORIES***");
        List<Category> categories = categoryRest.getAllCategories();
        assertNotNull(categories);
        categories.forEach(x -> System.out.println(x.getName()));
    }

    @Test
    public void setNullCategory(
            @ArquillianResteasyResource(restResource) CategoryRest categoryRest) throws InternalException {
        expectedException.expect(ConstraintViolationException.class);
        Category category = new Category();
        categoryRest.createCategory(category);
    }

    @Test
    @InSequence(1)
    public void testCreateCategory(
            @ArquillianResteasyResource(restResource) CategoryRest categoryRest) throws InternalException {
        System.out.println("SEQUENCE 1");
        categoryRest.createCategory(testCategory);
        
    }

    @Test
    @InSequence(2)
    public void testReadCategory(
            @ArquillianResteasyResource(restResource) CategoryRest categoryRest) throws InternalException {
        System.out.println("SEQUENCE 2");        
        assertEquals(testCategory, getCategoryByName(categoryRest, testCategory.getName()));
    }
    
    @Test
    @InSequence(2)
    public void testUpdateCategory(
            @ArquillianResteasyResource(restResource) CategoryRest categoryRest) throws InternalException {
        System.out.println("SEQUENCE 3");
        Category foundCategory = getCategoryByName(categoryRest, testCategory.getName());
        //testCategory.setDescription("OTHER_DESCRIPTION");
        testCategory.setName("DummyName");
        categoryRest.updateCategory(foundCategory.getId(), testCategory);
        //assertEquals("OTHER_DESCRIPTION", getCategoryByName(categoryRest, testCategory.getName()).getDescription());
        assertNotNull(getCategoryByName(categoryRest, "DummyName"));
    }

    @Test
    @InSequence(3)
    public void testDeleteCategory(
            @ArquillianResteasyResource(restResource) CategoryRest categoryRest) throws InternalException {
        System.out.println("SEQUENCE 4");                
        Category foundCategory = getCategoryByName(categoryRest, testCategory.getName());
        categoryRest.deleteCategory(foundCategory.getId());
        assertEquals(null,categoryRest.getCategoryById(foundCategory.getId()));

    }

}
