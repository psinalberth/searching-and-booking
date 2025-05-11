package com.github.psinalberth.notification.dtos;

import com.github.psinalberth.booking.dtos.BookingEvent;
import com.github.psinalberth.shared.dtos.UserInfo;

public record BookingNotificationEvent(String bookingId, BookingEvent event, UserInfo userInfo) {
}
