package com.github.psinalberth.notification.service;

import com.github.psinalberth.notification.dtos.BookingNotificationEvent;
import com.github.psinalberth.notification.dtos.NotificationDto;
import com.github.psinalberth.notification.dtos.NotificationProperties;
import com.github.psinalberth.notification.dtos.UserInfo;
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
    private final UserInfo userInfo;

    public ExternalBookingNotificationService(
            final KafkaTemplate<String, Object> kafkaTemplate,
            @Value("${app.config.message.notification-request}") final String topic,
            final NotificationProperties notificationProperties,
            final UserInfo userInfo
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
        this.notificationProperties = notificationProperties;
        this.userInfo = userInfo;
    }

    @Override
    public void sendNotification(final BookingNotificationEvent notification) {
        var properties = notificationProperties.forType(notification.event().type());
        kafkaTemplate.send(topic, new NotificationDto(properties.getTemplateId(), userInfo));
    }
}
