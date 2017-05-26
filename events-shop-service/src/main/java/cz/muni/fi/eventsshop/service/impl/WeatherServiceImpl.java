package cz.muni.fi.eventsshop.service.impl;

import cz.muni.fi.eventsshop.service.WeatherService;

import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by patrik.cyprian on 26.5.2017.
 */
@ApplicationScoped
public class WeatherServiceImpl implements WeatherService {

    private final String USER_AGENT = "Mozilla/5.0";
    private final String API_KEY = "b9070dcb23fe31d4c7ce070b7d199666";
    private final String CZ_CODE = "cz";


    @Override
    public String getWheatherForecast(String cityName) throws Exception {
        String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + cityName +"," +  CZ_CODE + "&APPID=" + API_KEY;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        //responseCode
        int responseCode = con.getResponseCode();
        //Log.debug("\nSending 'GET' request to URL : " + url);
        //Log.debug("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //return JSON string from http://www.openweathermap.com/
        return response.toString();
    }
}
