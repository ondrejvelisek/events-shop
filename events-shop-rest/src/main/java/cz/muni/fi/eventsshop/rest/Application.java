package cz.muni.fi.eventsshop.rest;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.facade.CategoryFacade;
import cz.muni.fi.eventsshop.facade.EventFacade;
import cz.muni.fi.eventsshop.facade.ServiceFacade;
import cz.muni.fi.eventsshop.facade.UserFacade;
import cz.muni.fi.eventsshop.model.Category;
import cz.muni.fi.eventsshop.model.Event;
import cz.muni.fi.eventsshop.model.Service;
import cz.muni.fi.eventsshop.model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.InternalServerErrorException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 *
 */
@ApplicationScoped
@ApplicationPath("api/v0.1")
public class Application extends javax.ws.rs.core.Application {

	@Inject
	private UserFacade userFacade;
	@Inject
	private CategoryFacade categoryFacade;
	@Inject
	private ServiceFacade serviceFacade;
	@Inject
	private EventFacade eventFacade;

	@PostConstruct
	public void init() {
		try {
			User ondrej = new User();
			ondrej.setOAuthId("105202389642828188450");
			ondrej.setRoles(new HashSet<>(Arrays.asList(User.Role.USER, User.Role.ADMIN)));
			ondrej.setName("Ondrej Velisek");
			ondrej.setEmail("ondrejvelisek@gmail.com");
			ondrej = userFacade.createUser(ondrej);

			User peter = new User();
			peter.setOAuthId("115200590838015842781");
			peter.setRoles(new HashSet<>(Arrays.asList(User.Role.USER, User.Role.ADMIN)));
			peter.setName("Peter Javorka");
			peter.setEmail("javorka93@gmail.com");
			userFacade.createUser(peter);

			Category food = new Category();
			food.setName("Food and drinks");
			food.setDescription("Every host know that happiness goes through stomach of his guests");
			food = categoryFacade.createCategory(food);

			Category entertainment = new Category();
			entertainment.setName("Entertainment");
			entertainment.setDescription("Do you need music, cultural show or teambuilding activity?");
			entertainment = categoryFacade.createCategory(entertainment);

			Category transportation = new Category();
			transportation.setName("Transportation");
			transportation.setDescription("We will take all of you to the event place and back. Or do you prefer luxury limousine?");
			transportation = categoryFacade.createCategory(transportation);

			Service coffee = new Service();
			coffee.setName("Coffee and tea");
			coffee.setDescription("Coffee break including coffee, tea, water and cookies");
			coffee.setPrice(BigDecimal.valueOf(22));
			coffee.setCategory(food);
			coffee = serviceFacade.createService(coffee);

			Service raut = new Service();
			raut.setName("Raut");
			raut.setDescription("Common table full of salty and sweet meals with plates where anybody can get what s/he wants");
			raut.setPrice(BigDecimal.valueOf(55));
			raut.setCategory(food);
			raut = serviceFacade.createService(raut);

			Service dinner = new Service();
			dinner.setName("Dinner");
			dinner.setDescription("Proper fancy dinner in restaurant");
			dinner.setPrice(BigDecimal.valueOf(160));
			dinner.setCategory(food);
			dinner = serviceFacade.createService(dinner);

			Service drinks = new Service();
			drinks.setName("Drink");
			drinks.setDescription("Beer, wine, coctails, and more");
			drinks.setPrice(BigDecimal.valueOf(155));
			drinks.setCategory(food);
			drinks = serviceFacade.createService(drinks);

			Service minibus = new Service();
			minibus.setName("Minibus");
			minibus.setDescription("Best an cheapest way how to transport group of people together");
			minibus.setPrice(BigDecimal.valueOf(108));
			minibus.setCategory(transportation);
			minibus = serviceFacade.createService(minibus);

			Service limousine = new Service();
			limousine.setName("Limousine");
			limousine.setDescription("Always a good way how to arrive to any social event");
			limousine.setPrice(BigDecimal.valueOf(565));
			limousine.setCategory(transportation);
			limousine = serviceFacade.createService(limousine);

			Service dj = new Service();
			dj.setName("DJ music");
			dj.setDescription("Want to wake up your guests with some dance? This is for you");
			dj.setPrice(BigDecimal.valueOf(1050));
			dj.setCategory(entertainment);
			dj = serviceFacade.createService(dj);

			Service liveMusic = new Service();
			liveMusic.setName("Music band");
			liveMusic.setDescription("We are able to find proper band specially for your event");
			liveMusic.setPrice(BigDecimal.valueOf(1900));
			liveMusic.setCategory(entertainment);
			liveMusic = serviceFacade.createService(liveMusic);

			Service moderator = new Service();
			moderator.setName("Moderator");
			moderator.setDescription("Professional who will guide your event whole evening");
			moderator.setPrice(BigDecimal.valueOf(1500));
			moderator.setCategory(entertainment);
			moderator = serviceFacade.createService(moderator);

			Service laserGame = new Service();
			laserGame.setName("Laser game");
			laserGame.setDescription("Ideal way how to develope healthy relationships in your team");
			laserGame.setPrice(BigDecimal.valueOf(180));
			laserGame.setCategory(entertainment);
			laserGame = serviceFacade.createService(laserGame);

			Event teambuilding = new Event();
			teambuilding.setName("Teambuilding");
			teambuilding.setClient(ondrej);
			teambuilding.setDate(new Date());
			teambuilding = eventFacade.createEvent(teambuilding);

			Event birthday = new Event();
			birthday.setName("Birthday party");
			birthday.setClient(ondrej);
			birthday.setDate(new Date());
			birthday = eventFacade.createEvent(birthday);

		} catch (InternalException e) {
			throw new InternalServerErrorException(e);
		}
	}

}
