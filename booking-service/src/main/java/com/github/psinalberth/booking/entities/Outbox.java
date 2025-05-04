package com.github.psinalberth.booking.entities;

import com.github.psinalberth.booking.dtos.OutboxStatus;

import java.time.LocalDateTime;

public record Outbox(
        String id,
        OutboxStatus status,
        String payload,
        LocalDateTime createdAt
) {
}
