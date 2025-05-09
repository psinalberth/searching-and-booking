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

    public static BookingEvent of(final BookingEventType type, final BookingDto booking) {
        return new BookingEvent(
                booking.eventId(),
                booking.userId(),
                type,
                booking.status()
        );
    }

    public static BookingEvent of(final CreateBookingDto booking, final BookingStatus status) {
        return new BookingEvent(
                booking.eventId(),
                booking.userId(),
                BookingEventType.REQUEST,
                status
        );
    }
}
