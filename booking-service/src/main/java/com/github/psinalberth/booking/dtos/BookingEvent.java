package com.github.psinalberth.booking.dtos;

import com.github.psinalberth.booking.enums.BookingEventType;
import com.github.psinalberth.booking.enums.BookingStatus;

public record BookingEvent(
        String eventId,
        String user,
        BookingEventType type,
        BookingStatus status
) {

    public BookingEvent(String eventId, String user, BookingEventType type) {
        this(eventId, user, type, null);
    }
}
