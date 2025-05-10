package com.github.psinalberth.notification.listeners;

import com.github.psinalberth.notification.dtos.NotificationDto;
import com.github.psinalberth.notification.service.NotificationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationRequestListener {

    private final Set<NotificationProvider> notificationProviders;

    @KafkaListener(
            topics = "${app.config.message.notification-request}",
            containerFactory = "containerFactory"
    )
    public void onEventReceived(final NotificationDto notification) {
        log.info("Received notification: {}", notification);
        notificationProviders.forEach(notificationProvider -> {
            try {
                notificationProvider.send(notification);
            } catch (Exception e) {
                log.error("Error sending notification: {}", e.getMessage());
            }
        });
    }
}
