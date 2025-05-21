package com.github.psinalberth.booking.dtos;

import com.github.psinalberth.shared.dtos.UserInfo;

public record CreateWaitlistDto(
        String eventId,
        UserInfo userInfo
) {
}
