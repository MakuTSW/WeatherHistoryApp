package com.weatherhistoryapp.controller.response;

import java.time.LocalDateTime;

public record WeatherHistoryResponse(Double averageTemperature, LocalDateTime sunriseTime, LocalDateTime sunsetTime) {
}
