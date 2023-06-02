package com.weatherhistoryapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherhistoryapp.controller.response.WeatherHistoryResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Iterator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenMeteoJsonMapper {

    public static WeatherHistoryResponse mapToWeatherHistoryResponse(String responseFromOpenMeteo) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseFromOpenMeteo);
        LocalDateTime sunrise = getSunrise(jsonNode);
        LocalDateTime sunset = getSunset(jsonNode);
        Double averageTemperature = getAverageTemperature(jsonNode);
        return new WeatherHistoryResponse(averageTemperature, sunrise, sunset);
    }

    private static Double getAverageTemperature(JsonNode jsonNode) {
        JsonNode arrayOfTemperatures = jsonNode.get("hourly").get("temperature_2m");
        Iterator<JsonNode> iterator = arrayOfTemperatures.iterator();
        double sumOfTemperatures = 0;
        while (iterator.hasNext()) {
            double temperature = iterator.next().asDouble();
            sumOfTemperatures += temperature;
        }
        return sumOfTemperatures/24;
    }

    private static LocalDateTime getSunrise(JsonNode jsonNode) {
        String sunrise = jsonNode.get("daily").get("sunrise").iterator().next().asText();
        return LocalDateTime.parse(sunrise);
    }

    private static LocalDateTime getSunset(JsonNode jsonNode) {
        String sunset = jsonNode.get("daily").get("sunset").iterator().next().asText();
        return LocalDateTime.parse(sunset);
    }
}
