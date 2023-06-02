package com.weatherhistoryapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.weatherhistoryapp.client.OpenMeteoClient;
import com.weatherhistoryapp.controller.request.WeatherHistoryRequest;
import com.weatherhistoryapp.controller.response.WeatherHistoryResponse;
import com.weatherhistoryapp.model.RequestHistory;
import com.weatherhistoryapp.repository.RequestHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class WeatherHistoryService {

    private final RequestHistoryRepository requestHistoryRepository;
    private final OpenMeteoClient openMeteoClient;

    public ResponseEntity<WeatherHistoryResponse> getWeatherHistory(WeatherHistoryRequest request) {
        saveRequest(request);

        String responseFromOpenMeteo = openMeteoClient.getResponseFromOpenMeteo(request);
        try {
            WeatherHistoryResponse response = OpenMeteoJsonMapper.mapToWeatherHistoryResponse(responseFromOpenMeteo);
            return ResponseEntity.ok(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeJsonMappingException("Cannot process response from Open Meteo");
        }
    }

    private void saveRequest(WeatherHistoryRequest request) {
        RequestHistory requestHistory = RequestHistory.builder()
                .time(LocalDateTime.now())
                .latitude(request.latitude())
                .longitude(request.longitude()).build();
        requestHistoryRepository.save(requestHistory);
    }
}
