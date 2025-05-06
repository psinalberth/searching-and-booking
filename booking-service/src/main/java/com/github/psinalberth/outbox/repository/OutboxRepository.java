package com.github.psinalberth.outbox.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.psinalberth.outbox.enums.OutboxStatus;
import com.github.psinalberth.outbox.enums.OutboxType;
import com.github.psinalberth.outbox.dtos.OutboxDto;
import com.github.psinalberth.outbox.entities.OutboxMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OutboxRepository {

    private final OutboxDatabaseRepository delegate;
    private final OutboxMapper outboxMapper;
    private final ObjectMapper objectMapper;

    @Transactional
    public <T> OutboxDto save(final OutboxType type, final T payload) {

        try {
            var json = objectMapper.writeValueAsString(payload);
            var entity = delegate.save(outboxMapper.toEntity(type, json));
            return outboxMapper.toDto(entity);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void updateStatus(final String outboxId, final OutboxStatus status) {
        delegate.findById(outboxId)
                .ifPresent(outbox -> delegate.save(outbox.withStatus(status)));
    }

    public List<OutboxDto> findAllPendingByType(final OutboxType type) {
        return delegate.findAllByTypeAndStatus(type, OutboxStatus.CREATED)
                .stream().map(outboxMapper::toDto)
                .toList();
    }
}
