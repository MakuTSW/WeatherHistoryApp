package com.weatherhistoryapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requests")
public class RequestHistory {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime time;

    private Double latitude;

    private Double longitude;
}
