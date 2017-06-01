package cz.muni.fi.eventsshop.rest;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.facade.CategoryFacade;
import cz.muni.fi.eventsshop.service.DTO.EventDTO;
import cz.muni.fi.eventsshop.service.DTO.ServiceDTO;
import cz.muni.fi.eventsshop.facade.EventFacade;
import cz.muni.fi.eventsshop.facade.ServiceFacade;
import cz.muni.fi.eventsshop.facade.UserFacade;
import cz.muni.fi.eventsshop.model.Category;
import cz.muni.fi.eventsshop.model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.InternalServerErrorException;
import java.math.BigDecimal;
import java.util.Arrays;
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

			User patrik = new User();
			patrik.setOAuthId("112491319501508959655");
			patrik.setRoles(new HashSet<>(Arrays.asList(User.Role.USER, User.Role.ADMIN)));
			patrik.setName("Patrik Cyprian");
			patrik.setEmail("cyprian.patrik@gmail.com");
			patrik = userFacade.createUser(patrik);


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

			ServiceDTO coffee = new ServiceDTO();
			coffee.setName("Coffee and tea");
			coffee.setDescription("Coffee break including coffee, tea, water and cookies");
			coffee.setPrice(BigDecimal.valueOf(22));
			coffee.setCategoryId(food.getId());
			coffee = serviceFacade.createService(coffee);

			ServiceDTO raut = new ServiceDTO();
			raut.setName("Raut");
			raut.setDescription("Common table full of salty and sweet meals with plates where anybody can get what s/he wants");
			raut.setPrice(BigDecimal.valueOf(55));
			raut.setCategoryId(food.getId());
			raut = serviceFacade.createService(raut);

			ServiceDTO dinner = new ServiceDTO();
			dinner.setName("Dinner");
			dinner.setDescription("Proper fancy dinner in restaurant");
			dinner.setPrice(BigDecimal.valueOf(160));
			dinner.setCategoryId(food.getId());
			dinner = serviceFacade.createService(dinner);

			ServiceDTO drinks = new ServiceDTO();
			drinks.setName("Drink");
			drinks.setDescription("Beer, wine, coctails, and more");
			drinks.setPrice(BigDecimal.valueOf(155));
			drinks.setCategoryId(food.getId());
			drinks = serviceFacade.createService(drinks);

			ServiceDTO minibus = new ServiceDTO();
			minibus.setName("Minibus");
			minibus.setDescription("Best an cheapest way how to transport group of people together");
			minibus.setPrice(BigDecimal.valueOf(108));
			minibus.setCategoryId(transportation.getId());
			minibus = serviceFacade.createService(minibus);

			ServiceDTO limousine = new ServiceDTO();
			limousine.setName("Limousine");
			limousine.setDescription("Always a good way how to arrive to any social event");
			limousine.setPrice(BigDecimal.valueOf(565));
			limousine.setCategoryId(transportation.getId());
			limousine = serviceFacade.createService(limousine);

			ServiceDTO dj = new ServiceDTO();
			dj.setName("DJ music");
			dj.setDescription("Want to wake up your guests with some dance? This is for you");
			dj.setPrice(BigDecimal.valueOf(1050));
			dj.setCategoryId(entertainment.getId());
			dj = serviceFacade.createService(dj);

			ServiceDTO liveMusic = new ServiceDTO();
			liveMusic.setName("Music band");
			liveMusic.setDescription("We are able to find proper band specially for your event");
			liveMusic.setPrice(BigDecimal.valueOf(1900));
			liveMusic.setCategoryId(entertainment.getId());
			liveMusic = serviceFacade.createService(liveMusic);

			ServiceDTO moderator = new ServiceDTO();
			moderator.setName("Moderator");
			moderator.setDescription("Professional who will guide your event whole evening");
			moderator.setPrice(BigDecimal.valueOf(1500));
			moderator.setCategoryId(entertainment.getId());
			moderator = serviceFacade.createService(moderator);

			ServiceDTO laserGame = new ServiceDTO();
			laserGame.setName("Laser game");
			laserGame.setDescription("Ideal way how to develope healthy relationships in your team");
			laserGame.setPrice(BigDecimal.valueOf(180));
			laserGame.setCategoryId(entertainment.getId());
			laserGame = serviceFacade.createService(laserGame);

			EventDTO teambuilding = new EventDTO();
			teambuilding.setName("Teambuilding");
			teambuilding.setClient(ondrej.getId());
			teambuilding.setDate(new Date());
			teambuilding.setAddress("Luzanecka 4");
			teambuilding.setCity("Brno");
			teambuilding = eventFacade.createEvent(teambuilding);

			EventDTO birthday = new EventDTO();
			birthday.setName("Birthday party");
			birthday.setClient(ondrej.getId());
			birthday.setDate(new Date());
			birthday.setAddress("Botanicka 8");
			birthday.setCity("Brno");
			birthday = eventFacade.createEvent(birthday);

			EventDTO weekend = new EventDTO();
			weekend.setName("Weekend party");
			weekend.setClient(patrik.getId());
			weekend.setCity("Brno");
			weekend.setAddress("FI MUNI");
			weekend.setDate(new Date(117, 5, 04));
			weekend = eventFacade.createEvent(weekend);

			weekend = new EventDTO();
			weekend.setName("Weekend party 2 = AfterParty");
			weekend.setCity("Brno");
			weekend.setAddress("FI MUNI");
			weekend.setClient(patrik.getId());
			weekend.setDate(new Date(117,5,05));
			weekend = eventFacade.createEvent(weekend);

		} catch (InternalException e) {
			throw new InternalServerErrorException(e);
		}
	}

}
