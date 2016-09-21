package com.accintern.ricardarmankuodis.weatherapp.weather;

/**
 * Created by ricard.arman.kuodis on 9/21/2016.
 */

public class Currently extends BaseWeather {

    private double temperature;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return super.toString()+"Currently{" +
                "temperature=" + temperature +
                '}';
    }
}
