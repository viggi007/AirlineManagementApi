package org.example.sample1.service;

import lombok.RequiredArgsConstructor;
import org.example.sample1.dto.BookingRequest;
import org.example.sample1.repository.BookingRepository;
import org.example.sample1.repository.FlightRepository;
import org.example.sample1.repository.PassengerRepository;
import org.example.sample1.exception.ResourceNotFoundException;
import org.example.sample1.model.Booking;
import org.example.sample1.model.Flight;
import org.example.sample1.model.Passenger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingService {
    private final BookingRepository repo;
    private final FlightRepository flightRepo;
    private final PassengerRepository passengerRepo;

    public List<Booking> findAll() {
        return repo.findAll();
    }

    public Booking findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found with id: " + id));
    }

    public Booking save(Booking b) {
        return repo.save(b);
    }

    public Booking createBooking(Long flightId, Long passengerId, String seatNumber) {
        Flight flight = flightRepo.findById(flightId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Flight not found with id: " + flightId));
        Passenger passenger = passengerRepo.findById(passengerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Passenger not found with id: " + passengerId));
        Booking booking = new Booking(null, flight, passenger, seatNumber);
        return repo.save(booking);
    }

    public Booking updateBooking(Long id, BookingRequest request) {
        Booking booking = findById(id);
        booking.setSeatNumber(request.seatNumber());
        return save(booking);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Booking not found with id: " + id);
        }
        repo.deleteById(id);
    }
}