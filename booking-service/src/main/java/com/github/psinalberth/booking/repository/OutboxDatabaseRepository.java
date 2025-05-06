package com.github.psinalberth.booking.repository;

import com.github.psinalberth.booking.dtos.OutboxStatus;
import com.github.psinalberth.booking.dtos.OutboxType;
import com.github.psinalberth.booking.entities.OutboxEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutboxDatabaseRepository extends MongoRepository<OutboxEntity, String> {

    List<OutboxEntity> findAllByTypeAndStatus(final OutboxType type, final OutboxStatus status);
}
