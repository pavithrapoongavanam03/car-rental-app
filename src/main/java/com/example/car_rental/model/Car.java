package com.example.car_rental.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    private String brand;
    private String model;
    private String fuelType;
    private Double pricePerDay;
}
