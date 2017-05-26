package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.service.WeatherService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Created by pato on 26.5.2017.
 */
@Transactional
@ApplicationScoped
public class WeatherFacadeImpl implements WeatherFacade {

    @Inject
    private WeatherService weatherService;

    @Override
    public String getWheatherForecast(String cityName) throws Exception {
        return weatherService.getWheatherForecast(cityName);
    }
}
