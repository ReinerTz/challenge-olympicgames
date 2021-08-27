package com.challenge.olympicgames.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class WeatherModel {
    public WeatherModel() {
        this.main = new Main();
    }

    private Main main;

    @Getter
    @Setter
    public static class Main {
        private double temp;
        private double humidity;
    }
}
