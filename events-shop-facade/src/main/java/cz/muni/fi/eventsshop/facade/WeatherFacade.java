package cz.muni.fi.eventsshop.facade;

/**
 * Created by patrik.cyprian on 26.5.2017.
 */
public interface WeatherFacade {

    String getWeatherForecast(String cityName) throws Exception;
}
