package org.example.sample1.controller;

import org.example.sample1.dto.BookingRequest;
import org.example.sample1.dto.BookingResponse;
import org.example.sample1.mapper.BookingMapper;
import org.example.sample1.model.Booking;
import org.example.sample1.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

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
        return ResponseEntity.ok(service
                .findAll()
                .stream()
                .map(BookingMapper::toResponse)
                .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                BookingMapper.toResponse(service.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<BookingResponse> create(
            @Valid @RequestBody BookingRequest request) {
        Booking created = service.createBooking(
                request.flightId(),
                request.passengerId(),
                request.seatNumber()
        );
        return ResponseEntity.created(
                URI.create("/api/bookings/" + created.getId())
        ).body(BookingMapper.toResponse(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody BookingRequest request) {
        Booking updated = service.updateBooking(id, request);
        return ResponseEntity.ok(BookingMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}