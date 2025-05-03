package com.example.car_rental.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponseDTO {
    private Long bookingId;

    private String carBrand;
    private String carModel;
    private String carFuelType;
    private Double carPricePerDay;

    private String userFullName;

    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalPrice;
}
