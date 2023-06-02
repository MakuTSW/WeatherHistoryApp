package com.weatherhistoryapp.controller;

import com.weatherhistoryapp.controller.request.WeatherHistoryRequest;
import com.weatherhistoryapp.controller.response.WeatherHistoryResponse;
import com.weatherhistoryapp.service.WeatherHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/weather/history")
@RequiredArgsConstructor
public class WeatherHistoryController {

    private final WeatherHistoryService weatherHistoryService;

    @GetMapping
    public ResponseEntity<WeatherHistoryResponse> getWeatherHistory(@RequestBody WeatherHistoryRequest request) {
        if (!request.isValid()) {
            return ResponseEntity.badRequest().build();
        }
        return weatherHistoryService.getWeatherHistory(request);
    }
}
