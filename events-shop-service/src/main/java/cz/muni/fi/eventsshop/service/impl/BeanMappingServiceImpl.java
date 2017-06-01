package cz.muni.fi.eventsshop.service.impl;

import cz.muni.fi.eventsshop.model.*;
import cz.muni.fi.eventsshop.service.BeanMappingService;
import cz.muni.fi.eventsshop.service.DTO.*;

import javax.enterprise.context.ApplicationScoped;
import java.util.stream.Collectors;

/**
 * Created by pato on 1.6.2017.
 */
@ApplicationScoped
public class BeanMappingServiceImpl implements BeanMappingService{

    @Override
    public Category toCategory(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        Category c = new Category();
        c.setName(dto.getName());
        c.setId(dto.getId());
        c.setDescription(dto.getDescription());
        return c;
    }

    @Override
    public CategoryDTO fromCategory(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

    @Override
    public Service toService(ServiceDTO dto) {
        if (dto == null) {
            return null;
        }
        Service service = new Service();
        service.setId(dto.getId());
        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setPrice(dto.getPrice());
        Category c = new Category();
        c.setId(dto.getCategoryId());
        service.setCategory(c);
        return service;
    }

    @Override
    public ServiceDTO fromService(Service service) {
        if (service == null) {
            return null;
        }
        ServiceDTO dto = new ServiceDTO();
        dto.setId(service.getId());
        dto.setName(service.getName());
        dto.setDescription(service.getDescription());
        dto.setPrice(dto.getPrice());
        dto.setCategoryId(service.getCategory().getId());
        return dto;
    }

    @Override
    public Event toEvent(EventDTO dto) {
        if (dto == null) {
            return null;
        }
        Event event = new Event();
        User u = new User();
        u.setId(dto.getClient());
        event.setClient(u);
        event.setId(dto.getId());
        event.setCity(dto.getCity());
        event.setAddress(dto.getAddress());
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setState(dto.getState());
        event.setEventServices(dto.getEventServiceDTOs()
                .stream()
                .map(this::toEventService)
                .collect(Collectors.toList()));
        return event;
    }

    @Override
    public EventDTO fromEvent(Event event) {
        if (event == null) {
            return null;
        }
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setName(event.getName());
        dto.setAddress(event.getAddress());
        dto.setCity(event.getCity());
        dto.setDate(event.getDate());
        dto.setClient(event.getClient().getId());
        dto.setState(event.getState());
        dto.setEventServiceDTOs(event.getEventServices()
                .stream()
                .map(this::fromEventService)
                .collect(Collectors.toList()));
        return dto;
    }

    @Override
    public User toUser(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User u = new User();
        u.setEmail(dto.getEmail());
        u.setName(dto.getName());
        u.setId(dto.getId());
        u.setOAuthId(dto.getoAuthId());
        u.setRoles(dto.getRoles());
        return u;
    }

    @Override
    public UserDTO fromUser(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setoAuthId(user.getOAuthId());
        dto.setRoles(user.getRoles());
        dto.setName(user.getName());
        return dto;
    }

    private EventService toEventService(EventServiceDTO dto) {
        if (dto == null) {
            return null;
        }
        EventService es = new EventService();

        Service s = new Service();
        s.setId(dto.getServiceId());

        es.setService(s);
        es.setId(dto.getId());
        es.setCount(dto.getCount());
        return es;
    }

    private EventServiceDTO fromEventService(EventService service) {
        if (service == null) {
            return null;
        }
        EventServiceDTO dto = new EventServiceDTO();
        dto.setId(service.getId());
        dto.setCount(service.getCount());
        return dto;
    }
}
