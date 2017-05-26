package cz.muni.fi.eventsshop.service;

/**
 * Created by patrik.cyprian on 26.5.2017.
 * Responsible for getting weather information from http://www.openweathermap.com/ API
 */
public interface WeatherService {

    String getWheatherForecast(String cityName) throws Exception;
}
