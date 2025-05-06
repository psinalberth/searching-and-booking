package com.github.psinalberth.outbox.repository;

import com.github.psinalberth.outbox.enums.OutboxStatus;
import com.github.psinalberth.outbox.enums.OutboxType;
import com.github.psinalberth.outbox.entities.OutboxEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutboxDatabaseRepository extends MongoRepository<OutboxEntity, String> {

    List<OutboxEntity> findAllByTypeAndStatus(final OutboxType type, final OutboxStatus status);
}
