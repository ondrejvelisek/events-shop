package cz.muni.fi.eventsshop.service;

import cz.muni.fi.eventsshop.service.impl.Weather;

/**
 * Created by patrik.cyprian on 26.5.2017.
 * Responsible for getting weather information from http://www.openweathermap.com/ API
 */
public interface WeatherService {

    Weather getWeatherForecast(String cityName) throws Exception;
}
