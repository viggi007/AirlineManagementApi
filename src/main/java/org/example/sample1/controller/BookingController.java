package org.example.sample1.controller;

import org.example.sample1.dto.BookingRequest;
import org.example.sample1.dto.BookingResponse;
import org.example.sample1.model.Booking;
import org.example.sample1.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@Validated
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAll() {
        List<BookingResponse> list = service.findAll().stream().map(this::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getById(@PathVariable Long id) {
        Booking b = service.findById(id);
        return ResponseEntity.ok(toResponse(b));
    }

    @PostMapping
    public ResponseEntity<BookingResponse> create(@Valid @RequestBody BookingRequest request) {
        Booking created = service.createBooking(request.getFlightId(), request.getPassengerId(), request.getSeatNumber());
        BookingResponse resp = toResponse(created);
        return ResponseEntity.created(URI.create("/api/bookings/" + created.getId())).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> update(@PathVariable Long id, @Valid @RequestBody BookingRequest request) {
        Booking booking = service.findById(id);
        // update relations
        booking.setSeatNumber(request.getSeatNumber());
        // if you want to update flight/passenger, fetch them as service does or call service methods
        Booking saved = service.save(booking);
        return ResponseEntity.ok(toResponse(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private BookingResponse toResponse(Booking b) {
        BookingResponse r = new BookingResponse();
        r.setId(b.getId());
        r.setFlightId(b.getFlight() != null ? b.getFlight().getId() : null);
        r.setPassengerId(b.getPassenger() != null ? b.getPassenger().getId() : null);
        r.setSeatNumber(b.getSeatNumber());
        return r;
    }
}