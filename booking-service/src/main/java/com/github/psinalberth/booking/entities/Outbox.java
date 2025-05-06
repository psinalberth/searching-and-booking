package com.github.psinalberth.booking.entities;

import com.github.psinalberth.booking.dtos.OutboxStatus;
import com.github.psinalberth.booking.dtos.OutboxType;

import java.time.LocalDateTime;

public record Outbox(
        String id,
        OutboxType type,
        OutboxStatus status,
        String payload,
        LocalDateTime createdAt
) {
}
