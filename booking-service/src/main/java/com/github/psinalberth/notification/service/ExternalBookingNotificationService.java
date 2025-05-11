package com.github.psinalberth.notification.service;

import com.github.psinalberth.notification.dtos.BookingNotificationEvent;
import com.github.psinalberth.notification.dtos.NotificationDto;
import com.github.psinalberth.notification.dtos.NotificationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExternalBookingNotificationService implements NotificationProvider {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topic;
    private final NotificationProperties notificationProperties;

    public ExternalBookingNotificationService(
            final KafkaTemplate<String, Object> kafkaTemplate,
            @Value("${app.config.message.notification-request}") final String topic,
            final NotificationProperties notificationProperties
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
        this.notificationProperties = notificationProperties;
    }

    @Override
    public void sendNotification(final BookingNotificationEvent notification) {
        var properties = notificationProperties.forType(notification.event().type());
        kafkaTemplate.send(topic, new NotificationDto(properties.getTemplateId(), notification.userInfo()));
    }
}
