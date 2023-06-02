package com.weatherhistoryapp.client;

import com.weatherhistoryapp.controller.request.WeatherHistoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenMeteoClient {
    private final RestTemplate restTemplate;

    public OpenMeteoClient() {
        restTemplate = new RestTemplate();
    }

    public String getResponseFromOpenMeteo(WeatherHistoryRequest request) {
        String openMeteoForecastURL
                = "https://api.open-meteo.com/v1/forecast";
        String parameters = buildParameters(request);
        ResponseEntity<String> response
                = restTemplate.getForEntity(openMeteoForecastURL + "?" + parameters, String.class);
        return response.getBody();
    }

    private static String buildParameters(WeatherHistoryRequest request) {
        String latitudeParam = "latitude=" + request.latitude().toString();
        String longitudeParam = "longitude=" + request.longitude().toString();
        String temperatureParam = "hourly=temperature_2m";
        String sunriseAndSunsetParam = "daily=sunrise,sunset";
        String forecastDaysParam = "forecast_days=1";
        String timezoneParam = "timezone=Europe/Berlin";
        return latitudeParam + "&" + longitudeParam + "&" + temperatureParam + "&"
                + sunriseAndSunsetParam + "&" + forecastDaysParam + "&" + timezoneParam;
    }
}
