package com.example.car_rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // should cover all packages inside com.example.car_rental.*
public class CarRentalApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarRentalApplication.class, args);
    }
}
