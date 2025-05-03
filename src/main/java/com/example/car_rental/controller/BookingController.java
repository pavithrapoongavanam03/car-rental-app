package com.example.car_rental.controller;

import com.example.car_rental.dto.BookingResponseDTO;
import com.example.car_rental.model.Booking;
import com.example.car_rental.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // CREATE booking
    @PostMapping
    public BookingResponseDTO createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    // READ all bookings
    @GetMapping
    public List<BookingResponseDTO> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // READ booking by ID
    @GetMapping("/{id}")
    public BookingResponseDTO getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    // UPDATE booking
    @PutMapping("/{id}")
    public BookingResponseDTO updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        return bookingService.updateBooking(id, booking);
    }

    // DELETE booking
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}
