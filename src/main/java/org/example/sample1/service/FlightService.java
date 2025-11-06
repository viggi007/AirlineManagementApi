package org.example.sample1.service;
import org.example.sample1.repository.FlightRepository;
import org.example.sample1.model.Flight;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    private final FlightRepository repo;

    public FlightService(FlightRepository repo) {
        this.repo = repo;
    }

    public List<Flight> findAll() { return repo.findAll(); }
    public Flight findById(Long id) { return repo.findById(id).orElseThrow(); }
    public Flight save(Flight f) { return repo.save(f); }
    public void delete(Long id) { repo.deleteById(id); }
}