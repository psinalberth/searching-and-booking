package com.github.psinalberth.outbox.entities;

import com.github.psinalberth.outbox.enums.OutboxStatus;
import com.github.psinalberth.outbox.enums.OutboxType;
import com.github.psinalberth.outbox.dtos.OutboxDto;
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

    default OutboxDto toDto(final OutboxEntity entity) {
        return new OutboxDto(
                entity.id(),
                entity.type(),
                entity.status(),
                entity.payload().toJson(),
                entity.createdAt()
        );
    }
}
