package com.github.psinalberth.booking.entities;

import com.github.psinalberth.booking.dtos.OutboxStatus;
import com.github.psinalberth.booking.dtos.OutboxType;
import org.bson.Document;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface OutboxMapper {

    default OutboxEntity toEntity(final OutboxType type, final String payload) {
        return new OutboxEntity(
                UUID.randomUUID().toString(),
                type,
                OutboxStatus.CREATED,
                Document.parse(payload),
                LocalDateTime.now(),
                null
        );
    }

    default Outbox toDto(final OutboxEntity entity) {
        return new Outbox(
                entity.id(),
                entity.type(),
                entity.status(),
                entity.payload().toJson(),
                entity.createdAt()
        );
    }
}
