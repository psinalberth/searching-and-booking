package com.github.psinalberth.booking.dtos;

import com.github.psinalberth.shared.dtos.UserInfo;

public record CreateBookingDto(
        String eventId,
        UserInfo userInfo,
        String bookingId
) {
}
