package com.github.psinalberth.booking.dtos;

import java.time.LocalDateTime;

public record CreateBookingDto(
        String eventId,
        String userId,
        LocalDateTime date
) {
}
