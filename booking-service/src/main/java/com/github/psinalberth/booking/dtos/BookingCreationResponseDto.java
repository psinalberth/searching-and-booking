package com.github.psinalberth.booking.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record BookingCreationResponseDto(
        @Schema(example = "98864681-0af2-4130-b631-931e42dae71e")
        String id,

        @Schema(example = "http://api.domain.com/v1/bookings/98864681-0af2-4130-b631-931e42dae71e")
        String uri,

        @Schema(example = "2025-05-09T19:29:41.631Z")
        LocalDateTime timestamp
) {
}
