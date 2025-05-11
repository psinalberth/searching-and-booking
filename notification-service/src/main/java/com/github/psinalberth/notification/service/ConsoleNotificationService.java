package com.github.psinalberth.notification.service;

import com.github.psinalberth.notification.dtos.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnMissingBean(NotificationProvider.class)
public class ConsoleNotificationService implements NotificationProvider {

    @Override
    public void send(final NotificationDto notification) {
        log.info("Sending notification {}", notification);
    }
}
