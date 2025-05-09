package com.github.psinalberth.booking.dtos;

import java.time.LocalDateTime;

public record BookingCreationResponseDto(
        String id,
        String uri,
        LocalDateTime timestamp
) {
}
