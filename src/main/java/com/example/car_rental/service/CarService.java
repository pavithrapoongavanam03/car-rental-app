package com.example.car_rental.service;

import com.example.car_rental.model.Car;
import com.example.car_rental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car updatedCar) {
        Car existingCar = carRepository.findById(id).orElseThrow();
        updatedCar.setCarId(id); // Don't miss this line!
        return carRepository.save(updatedCar);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow();
    }
}
