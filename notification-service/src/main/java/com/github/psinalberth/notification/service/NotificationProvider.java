package com.github.psinalberth.notification.service;

import com.github.psinalberth.notification.dtos.NotificationDto;

public interface NotificationProvider {

    void send(final NotificationDto notification);
}
