package cz.muni.fi.eventsshop.service.impl;

/**
 * Created by pato on 31.5.2017.
 */
public class Weather {
    private float temperature;
    private float pressure;
    private String cloudDescription;
    private float windSpeed;
    private float windDegree;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public String getCloudDescription() {
        return cloudDescription;
    }

    public void setCloudDescription(String cloudDescription) {
        this.cloudDescription = cloudDescription;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(float windDegree) {
        this.windDegree = windDegree;
    }
}
