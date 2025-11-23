package org.example.sample1.dto;

public record BookingResponse (
    Long id,
    Long flightId,
    Long passengerId,
    String seatNumber) {

}