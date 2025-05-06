package com.github.psinalberth.outbox.dtos;

import com.github.psinalberth.outbox.enums.OutboxStatus;
import com.github.psinalberth.outbox.enums.OutboxType;

import java.time.LocalDateTime;

public record OutboxDto(
        String id,
        OutboxType type,
        OutboxStatus status,
        String payload,
        LocalDateTime createdAt
) {
}
