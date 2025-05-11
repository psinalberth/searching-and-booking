package com.github.psinalberth.notification.dtos;

import java.time.LocalDateTime;

public record NotificationTemplateDto(
        String id,
        String title,
        String mailBody,
        String messageBody,
        LocalDateTime created
) {
}
