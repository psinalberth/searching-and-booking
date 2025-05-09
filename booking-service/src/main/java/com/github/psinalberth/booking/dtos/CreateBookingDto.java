package com.github.psinalberth.booking.dtos;

public record CreateBookingDto(
        String eventId,
        String userId,
        String bookingId
) {
}
