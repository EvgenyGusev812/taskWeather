package com.evgeny.weather.repository;

import com.evgeny.weather.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Optional<Weather> getByDate(Date date);
}
