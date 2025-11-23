package org.example.sample1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookingRequest (
    @NotNull
    Long flightId,

    @NotNull
    Long passengerId,

    @NotNull
    @Size(min = 1, max = 10)
    String seatNumber) {

}