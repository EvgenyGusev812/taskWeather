package com.evgeny.weather.service;

import com.evgeny.weather.entity.Weather;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ParserService {

    private static String url = "https://yandex.ru";

    public static Weather getWeatherFromWebSite() throws IOException {
        URL urlInst = new URL(url);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlInst.openStream()))) {
            StringBuilder sb = new StringBuilder();
            String nextLine;
            while ((nextLine = bufferedReader.readLine()) != null) {
                sb.append(nextLine);
            }
            Pattern pattern = Pattern.compile("<div class='weather__temp'>(.+?)</div>");
            Matcher matcher = pattern.matcher(sb.toString());
            matcher.find();
            String temperature = matcher.group(1);
            temperature = temperature.substring(0, temperature.length() - 1);
            return new Weather().builder()
                    .date(new Date(System.currentTimeMillis()))
                    .value(temperature)
                    .build();
        }
    }
}
