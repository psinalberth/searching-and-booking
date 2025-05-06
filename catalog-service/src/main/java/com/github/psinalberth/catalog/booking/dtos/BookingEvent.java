package com.github.psinalberth.catalog.booking.dtos;

import com.github.psinalberth.catalog.booking.enums.BookingEventType;
import com.github.psinalberth.catalog.booking.enums.BookingStatus;

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
