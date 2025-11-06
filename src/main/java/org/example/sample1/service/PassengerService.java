package org.example.sample1.service;

import org.example.sample1.repository.PassengerRepository;
import org.example.sample1.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    private final PassengerRepository repo;

    public PassengerService(PassengerRepository repo) {
        this.repo = repo;
    }

    public List<Passenger> findAll() { return repo.findAll(); }
    public Passenger findById(Long id) { return repo.findById(id).orElseThrow(); }
    public Passenger save(Passenger f) { return repo.save(f); }
    public void delete(Long id) { repo.deleteById(id); }
}