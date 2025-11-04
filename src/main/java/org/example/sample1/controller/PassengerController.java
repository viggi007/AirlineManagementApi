package org.example.sample1.controller;

import org.example.sample1.model.Passenger;
import org.example.sample1.service.PassengerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService service;

    public PassengerController(PassengerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Passenger> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Passenger getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Passenger create(@RequestBody Passenger passenger) {
        return service.save(passenger);
    }

    @PutMapping("/{id}")
    public Passenger update(@PathVariable Long id, @RequestBody Passenger updated) {
        Passenger passenger = service.findById(id);
        passenger.setName(updated.getName());
        passenger.setEmail(updated.getEmail());
        return service.save(passenger);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
