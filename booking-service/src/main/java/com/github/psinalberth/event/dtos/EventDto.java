package com.github.psinalberth.event.dtos;

import java.time.LocalDateTime;

public record EventDto(
        String id,
        String title,
        EventStatus status,
        LocalDateTime date,
        LocalDateTime maxSubscriptionDate
) {

    public boolean isAvailableForSubscription() {
        return status == EventStatus.SUBSCRIPTION_AVAILABLE && !maxSubscriptionDate.isBefore(LocalDateTime.now());
    }
}
