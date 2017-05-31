package cz.muni.fi.eventsshop.service;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * Created by pato on 1.6.2017.
 */
public interface BeanMappingService {
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();
}
