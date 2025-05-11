package com.github.psinalberth.booking.dtos;

import com.github.psinalberth.booking.enums.BookingStatus;
import com.github.psinalberth.shared.dtos.UserInfo;

import java.time.LocalDateTime;
import java.util.List;

public record BookingDto(
        String id,
        UserInfo user,
        String eventId,
        BookingStatus status,
        LocalDateTime createdAt
) {

    public boolean isProcessed() {
        return status != null && List.of(BookingStatus.CONFIRMED, BookingStatus.REQUESTED).contains(status);
    }
}
