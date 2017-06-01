package cz.muni.fi.eventsshop.service;

import cz.muni.fi.eventsshop.model.Category;
import cz.muni.fi.eventsshop.model.Event;
import cz.muni.fi.eventsshop.model.Service;
import cz.muni.fi.eventsshop.model.User;
import cz.muni.fi.eventsshop.service.DTO.CategoryDTO;
import cz.muni.fi.eventsshop.service.DTO.EventDTO;
import cz.muni.fi.eventsshop.service.DTO.ServiceDTO;
import cz.muni.fi.eventsshop.service.DTO.UserDTO;

/**
 * Created by pato on 1.6.2017.
 */
public interface BeanMappingService {
    Category toCategory(CategoryDTO dto);
    CategoryDTO fromCategory(Category category);
    Service toService(ServiceDTO dto);
    ServiceDTO fromService(Service service);
    Event toEvent(EventDTO dto);
    EventDTO fromEvent(Event event);
    User toUser(UserDTO dto);
    UserDTO fromUser(User user);
}
