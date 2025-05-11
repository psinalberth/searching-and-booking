package com.github.psinalberth.notification.service;

import com.github.psinalberth.notification.dtos.BookingNotificationEvent;

public interface NotificationProvider {

    void sendNotification(final BookingNotificationEvent event);
}
