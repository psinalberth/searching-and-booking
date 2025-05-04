package com.github.psinalberth.booking.repository;

import com.github.psinalberth.booking.entities.OutboxEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxDatabaseRepository extends MongoRepository<OutboxEntity, String> {
}
