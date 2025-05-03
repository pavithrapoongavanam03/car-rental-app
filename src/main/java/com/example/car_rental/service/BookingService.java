package com.example.car_rental.service;

import com.example.car_rental.dto.BookingResponseDTO;
import com.example.car_rental.model.Booking;
import com.example.car_rental.model.Car;
import com.example.car_rental.model.User;
import com.example.car_rental.repository.BookingRepository;
import com.example.car_rental.repository.CarRepository;
import com.example.car_rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    // CREATE
    public BookingResponseDTO createBooking(Booking booking) {
        // Fetch car & user to make sure everything's synced
        Optional<Car> car = carRepository.findById(booking.getCar().getCarId());
        Optional<User> user = userRepository.findById(booking.getUser().getId());

        if (car.isPresent() && user.isPresent()) {
            booking.setCar(car.get());
            booking.setUser(user.get());
            Booking savedBooking = bookingRepository.save(booking);
            return mapToDTO(savedBooking);
        } else {
            throw new RuntimeException("Invalid Car or User ID");
        }
    }

    // READ ALL
    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // READ BY ID
    public BookingResponseDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return mapToDTO(booking);
    }

    // UPDATE
    public BookingResponseDTO updateBooking(Long id, Booking updatedBooking) {
        Booking existing = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Optional<Car> car = carRepository.findById(updatedBooking.getCar().getCarId());
        Optional<User> user = userRepository.findById(updatedBooking.getUser().getId());

        if (car.isPresent() && user.isPresent()) {
            existing.setCar(car.get());
            existing.setUser(user.get());
            existing.setStartDate(updatedBooking.getStartDate());
            existing.setEndDate(updatedBooking.getEndDate());
            existing.setTotalPrice(updatedBooking.getTotalPrice());

            Booking saved = bookingRepository.save(existing);
            return mapToDTO(saved);
        } else {
            throw new RuntimeException("Invalid Car or User ID");
        }
    }

    // DELETE
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    // Mapping to DTO
    private BookingResponseDTO mapToDTO(Booking booking) {
        return BookingResponseDTO.builder()
                .bookingId(booking.getBookingId())
                .carBrand(booking.getCar().getBrand())
                .carModel(booking.getCar().getModel())
                .carFuelType(booking.getCar().getFuelType())
                .carPricePerDay(booking.getCar().getPricePerDay())
                .userFullName(booking.getUser().getFullName())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .totalPrice(booking.getTotalPrice())
                .build();
    }
}
