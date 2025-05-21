package com.github.psinalberth.booking.dtos;

import com.github.psinalberth.shared.dtos.UserInfo;

import java.time.LocalDateTime;

public record WaitlistDto(
        String eventId,
        UserInfo user,
        LocalDateTime createdAt
) {
}
