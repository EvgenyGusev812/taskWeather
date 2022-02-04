package com.evgeny.weather.service;

import com.evgeny.weather.entity.Weather;
import com.evgeny.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather getTodayWeather() throws IOException {
        Optional<Weather> todayWeather =
                weatherRepository.getByDate(new Date(System.currentTimeMillis()));
        if (todayWeather.isEmpty()) {
            Weather weather = ParserService.getWeatherFromWebSite();
            weatherRepository.saveAndFlush(weather);
            return weather;
        }
        return todayWeather.get();
    }
}
