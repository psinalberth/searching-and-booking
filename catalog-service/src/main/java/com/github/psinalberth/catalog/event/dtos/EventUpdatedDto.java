package com.github.psinalberth.catalog.event.dtos;

import com.github.psinalberth.catalog.event.enums.EventStatus;

import java.time.LocalDateTime;

public record EventUpdatedDto(
        String id,
        String title,
        EventStatus status,
        LocalDateTime date,
        LocalDateTime maxSubscriptionDate
) {

    public static EventUpdatedDto of(final EventDto event) {
        return new EventUpdatedDto(
                event.id(),
                event.title(),
                event.status(),
                event.date(),
                event.maxSubscriptionDate()
        );
    }
}
