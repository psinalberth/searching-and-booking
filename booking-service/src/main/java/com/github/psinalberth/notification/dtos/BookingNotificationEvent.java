package com.github.psinalberth.notification.dtos;

import com.github.psinalberth.booking.dtos.BookingEvent;

public record BookingNotificationEvent(String bookingId, BookingEvent event) {
}
