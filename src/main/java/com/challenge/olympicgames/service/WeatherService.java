package com.challenge.olympicgames.service;

import com.challenge.olympicgames.model.WeatherModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class WeatherService {

    private final static String URL_API = "http://api.openweathermap.org/data/2.5/weather?q={city_name}&appid={api_key}";


    public WeatherModel.Main searchWeather() {
        ResponseEntity<WeatherModel> weather = new RestTemplate().getForEntity(URL_API, WeatherModel.class, "tokyo", "833c8a534b9f352a479d01b8a18ac6db");
        return Objects.requireNonNull(weather.getBody()).getMain();
    }
}
