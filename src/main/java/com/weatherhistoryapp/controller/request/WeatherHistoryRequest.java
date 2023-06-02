package com.weatherhistoryapp.controller.request;

public record  WeatherHistoryRequest(Double latitude, Double longitude) {

    public boolean isValid() {
        return latitude != null && longitude != null;
    }
}
