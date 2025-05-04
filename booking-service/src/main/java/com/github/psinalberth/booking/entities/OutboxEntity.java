package com.github.psinalberth.booking.entities;

import com.github.psinalberth.booking.dtos.OutboxStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("outbox")
public record OutboxEntity(
        String id,
        OutboxStatus status,
        org.bson.Document payload,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public OutboxEntity withStatus(final OutboxStatus status) {
        return new OutboxEntity(id(), status, payload(), createdAt(), LocalDateTime.now());
    }
}
