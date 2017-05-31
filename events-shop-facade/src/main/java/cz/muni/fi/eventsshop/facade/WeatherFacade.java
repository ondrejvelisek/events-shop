package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.service.impl.Weather;

/**
 * Created by patrik.cyprian on 26.5.2017.
 */
public interface WeatherFacade {

    Weather getWeatherForecast(String cityName) throws Exception;
}
