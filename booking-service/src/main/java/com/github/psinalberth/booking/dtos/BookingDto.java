package com.github.psinalberth.booking.dtos;

import com.github.psinalberth.booking.enums.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;

public record BookingDto(
        String id,
        String userId,
        String eventId,
        BookingStatus status,
        LocalDateTime date,
        LocalDateTime createdAt
) {

    public boolean isProcessed() {
        return status != null && List.of(BookingStatus.CONFIRMED, BookingStatus.REQUESTED).contains(status);
    }
}
