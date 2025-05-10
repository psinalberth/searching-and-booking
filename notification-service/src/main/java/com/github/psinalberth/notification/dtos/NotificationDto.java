package com.github.psinalberth.notification.dtos;

public record NotificationDto(
        String templateId,
        String title,
        UserInfo userInfo,
        String body
) {
}
