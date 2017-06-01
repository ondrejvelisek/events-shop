package cz.muni.fi.eventsshop.service.DTO;

import cz.muni.fi.eventsshop.model.Service;

/**
 * Created by pato on 1.6.2017.
 */
public class EventServiceDTO {

    private Long id;
    private Long serviceId;
    private Integer count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
