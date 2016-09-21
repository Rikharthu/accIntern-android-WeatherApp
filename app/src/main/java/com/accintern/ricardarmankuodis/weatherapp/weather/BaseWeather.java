package com.accintern.ricardarmankuodis.weatherapp.weather;

/**
 * Created by ricard.arman.kuodis on 9/21/2016.
 */

public abstract class BaseWeather {

    private long time;
    private String summary;
    private String icon;
    private double precipProbability;
    private double humidity;

    public BaseWeather() {
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "BaseWeather{" +
                "time=" + time +
                ", summary='" + summary + '\'' +
                ", icon='" + icon + '\'' +
                ", precipProbability=" + precipProbability +
                ", humidity=" + humidity +
                '}';
    }
}
