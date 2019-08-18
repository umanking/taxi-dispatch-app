package com.example.taxi.dto;

import com.example.taxi.domain.dispatch.DriverStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class DispatchDto {

    @Getter
    @Setter
    public static class DispatchResponse {
        private Long id;
        private String address;
        private String assignDriver;
        private DriverStatus driverStatus;
        private LocalDateTime requestAt;
        private LocalDateTime completedAt;
    }
}
