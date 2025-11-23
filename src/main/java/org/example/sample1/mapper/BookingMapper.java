package org.example.sample1.mapper;

import org.example.sample1.dto.BookingResponse;
import org.example.sample1.model.Booking;

public class BookingMapper {
    public static BookingResponse toResponse(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getFlight() != null ? booking.getFlight().getId() : null,
                booking.getPassenger() != null ? booking.getPassenger().getId() : null,
                booking.getSeatNumber()
        );
    }
}
