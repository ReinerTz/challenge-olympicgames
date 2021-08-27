package com.challenge.olympicgames.service;

import com.challenge.olympicgames.model.WeatherModel;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WeatherServiceTest {



    @Mock
    private RestTemplate restTemplate;


    @InjectMocks
    private WeatherService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private final static String URL_API = "http://api.openweathermap.org/data/2.5/weather?q={city_name}&appid={api_key}";

    @Test
    public void buscarTemperaturaTokyo() {
        WeatherModel weatherModel = new WeatherModel();
        weatherModel.getMain().setHumidity(10.4);
        weatherModel.getMain().setTemp(30.2);

        ResponseEntity<WeatherModel> response = new ResponseEntity<>(weatherModel, HttpStatus.OK);

        Mockito.when(restTemplate.getForEntity(URL_API, WeatherModel.class)).thenReturn(response);
        WeatherModel.Main weather = service.searchWeather();
        assertEquals(weatherModel.getMain().getHumidity(), weather.getHumidity());
        assertEquals(weatherModel.getMain().getTemp(), weather.getTemp());
    }

}
