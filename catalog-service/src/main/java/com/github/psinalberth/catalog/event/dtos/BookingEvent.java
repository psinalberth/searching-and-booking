package com.github.psinalberth.catalog.event.dtos;

public record BookingEvent(
        String eventId,
        String user,
        BookingEventType type,
        BookingStatus status
) {

    public BookingEvent confirmation() {
        return new BookingEvent(eventId(), user(), BookingEventType.CONFIRMATION, BookingStatus.CONFIRMED);
    }

    public BookingEvent cancellation(final BookingStatus status) {
        return new BookingEvent(eventId(), user(), BookingEventType.CANCELLATION, status);
    }
}
