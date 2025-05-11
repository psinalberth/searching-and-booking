package com.github.psinalberth.notification.dtos;

import com.github.psinalberth.shared.dtos.UserInfo;

public record NotificationDto(
        String templateId,
        UserInfo userInfo
) {
}
