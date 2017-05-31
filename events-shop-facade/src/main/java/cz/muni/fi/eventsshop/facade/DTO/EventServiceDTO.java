package cz.muni.fi.eventsshop.facade.DTO;

import cz.muni.fi.eventsshop.model.Service;

/**
 * Created by pato on 1.6.2017.
 */
public class EventServiceDTO {

    private long id;
    private Service service;
    private int count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "EventServiceDTO{" +
                "id=" + id +
                ", service=" + service +
                ", count=" + count +
                '}';
    }
}
