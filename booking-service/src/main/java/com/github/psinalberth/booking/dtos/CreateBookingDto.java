package com.github.psinalberth.booking.dtos;

import java.time.LocalDateTime;

public record CreateBookingDto(
        String eventId,
        String userId,
        LocalDateTime date,
        String bookingId
) {

    public CreateBookingDto withBookingId(final String bookingId) {
        return new CreateBookingDto(eventId(), userId(), date(), bookingId);
    }
}
