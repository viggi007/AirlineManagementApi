package org.example.sample1.dto;

public class BookingResponse {
    private Long id;
    private Long flightId;
    private Long passengerId;
    private String seatNumber;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public Long getPassengerId() { return passengerId; }
    public void setPassengerId(Long passengerId) { this.passengerId = passengerId; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
}