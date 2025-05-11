package com.github.psinalberth.notification.dtos;

public record NotificationDto(
        String templateId,
        UserInfo userInfo
) {
}
