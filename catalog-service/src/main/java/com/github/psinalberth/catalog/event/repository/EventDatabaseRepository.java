package com.github.psinalberth.catalog.event.repository;

import com.github.psinalberth.catalog.event.entities.EventEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDatabaseRepository extends MongoRepository<EventEntity, String> {
}
