package com.github.psinalberth.booking.dtos;

import com.github.psinalberth.shared.dtos.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record CreateBookingRequest(

        @Schema(example = "98864681-0af2-4130-b631-931e42dae71e")
        @NotEmpty(message = "Event id is required.")
        String eventId
) {

    public CreateBookingDto toBookingDto(final UserInfo userInfo, final String bookingId) {
        return new CreateBookingDto(eventId(), userInfo, bookingId);
    }
}
