package org.example.sample1.service;

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
public class BookingService {
    private final BookingRepository repo;
    private final FlightRepository flightRepo;
    private final PassengerRepository passengerRepo;

    public BookingService(BookingRepository repo, FlightRepository flightRepo, PassengerRepository passengerRepo) {
        this.repo = repo;
        this.flightRepo = flightRepo;
        this.passengerRepo = passengerRepo;
    }

    public List<Booking> findAll() { return repo.findAll(); }

    public Booking findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
    }

    @Transactional
    public Booking save(Booking b) { return repo.save(b); }

    @Transactional
    public Booking createBooking(Long flightId, Long passengerId, String seatNumber) {
        Flight flight = flightRepo.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + flightId));
        Passenger passenger = passengerRepo.findById(passengerId)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found with id: " + passengerId));
        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setPassenger(passenger);
        booking.setSeatNumber(seatNumber);
        return repo.save(booking);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Booking not found with id: " + id);
        }
        repo.deleteById(id);
    }
}