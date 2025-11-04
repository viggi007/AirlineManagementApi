package org.example.sample1.controller;

import org.example.sample1.model.Booking;
import org.example.sample1.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping
    public List<Booking> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Booking getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Booking create(@RequestBody Booking booking) {
        return service.save(booking);
    }

    @PutMapping("/{id}")
    public Booking update(@PathVariable Long id, @RequestBody Booking updated) {
        Booking booking = service.findById(id);
        booking.setSeatNumber(updated.getSeatNumber());
        booking.setFlight(updated.getFlight());
        booking.setPassenger(updated.getPassenger());
        return service.save(booking);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
