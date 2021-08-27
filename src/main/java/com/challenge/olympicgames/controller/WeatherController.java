package com.challenge.olympicgames.controller;

import com.challenge.olympicgames.model.WeatherModel;
import com.challenge.olympicgames.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    @GetMapping
    public WeatherModel.Main searchWeather(){
        return service.searchWeather();
    }
}
