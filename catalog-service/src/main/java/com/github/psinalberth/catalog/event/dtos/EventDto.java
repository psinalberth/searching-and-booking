package com.github.psinalberth.catalog.event.dtos;

import com.github.psinalberth.catalog.event.enums.EventStatus;

import java.time.LocalDateTime;
import java.util.List;

public record EventDto(
        String id,
        String title,
        String description,
        EventStatus status,
        List<String> amenities,
        Integer availableSpots,
        LocalDateTime date,
        LocalDateTime maxSubscriptionDate
) {
    public boolean hasAvailableSpots() {
        return availableSpots > 0;
    }
}
