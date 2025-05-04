package com.github.psinalberth.catalog.event.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record EventDto(
        String id,
        String title,
        String description,
        List<String> amenities,
        Integer availableSpots,
        LocalDateTime date
) {
    public boolean hasAvailableSpots() {
        return availableSpots > 0;
    }
}
