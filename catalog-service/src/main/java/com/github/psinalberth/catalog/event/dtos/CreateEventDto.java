package com.github.psinalberth.catalog.event.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record CreateEventDto(
        String title,
        String description,
        List<String> amenities,
        Integer availableSpots,
        LocalDateTime date
) {
}
