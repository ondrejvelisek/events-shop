package cz.muni.fi.eventsshop.rest;

import cz.muni.fi.eventsshop.facade.WeatherFacade;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * Created by patrik.cyprian on 26.5.2017.
 */
@ApplicationScoped
@Path("/weather")
public class WeatherRestImpl implements WeatherRest {

    @Inject
    private WeatherFacade weatherFacade;

    @Override
    public String getWeatherForecast(String cityName) throws Exception {
        return weatherFacade.getWeatherForecast(cityName);
    }
}
