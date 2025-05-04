package com.github.psinalberth.catalog.event.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SearchEventDto(
        String fulltextSearch,

        @NotNull(message = "Page number is required.")
        @Min(value = 1, message = "Page number must be greater than 0")
        Integer pageNumber,

        @NotNull(message = "Page size is required.")
        @Min(value = 1, message = "Page size must be greater than 0")
        @Max(value = 30, message = "Page size must be lesser than 31")
        Integer pageSize
) {
}
