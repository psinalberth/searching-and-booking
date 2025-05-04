package com.github.psinalberth.booking.dtos;

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
