package com.github.psinalberth.outbox.entities;

import com.github.psinalberth.outbox.enums.OutboxStatus;
import com.github.psinalberth.outbox.enums.OutboxType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("outbox")
public record OutboxEntity(
        String id,
        OutboxType type,
        OutboxStatus status,
        org.bson.Document payload,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public OutboxEntity withStatus(final OutboxStatus status) {
        return new OutboxEntity(id(), type(), status, payload(), createdAt(), LocalDateTime.now());
    }
}
