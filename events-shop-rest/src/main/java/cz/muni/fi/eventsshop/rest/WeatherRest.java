package cz.muni.fi.eventsshop.rest;

import cz.muni.fi.eventsshop.service.impl.Weather;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by patrik.cyprian on 26.5.2017.
 */
public interface WeatherRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Weather getWeatherForecast(String cityName) throws Exception;
}
