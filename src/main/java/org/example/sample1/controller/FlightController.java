package org.example.sample1.controller;


import org.example.sample1.service.FlightService;
import org.example.sample1.model.Flight;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    @GetMapping
    public List<Flight> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Flight getById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public Flight create(@RequestBody Flight flight) { return service.save(flight); }

    @PutMapping("/{id}")
    public Flight update(@PathVariable Long id, @RequestBody Flight updated) {
        Flight flight = service.findById(id);
        flight.setFlightNumber(updated.getFlightNumber());
        flight.setOrigin(updated.getOrigin());
        flight.setDestination(updated.getDestination());
        flight.setDepartureTime(updated.getDepartureTime());
        flight.setArrivalTime(updated.getArrivalTime());
        return service.save(flight);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
