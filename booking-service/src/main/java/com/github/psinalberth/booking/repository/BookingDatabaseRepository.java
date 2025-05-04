package com.github.psinalberth.booking.repository;

import com.github.psinalberth.booking.entities.BookingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingDatabaseRepository extends MongoRepository<BookingEntity, String> {

    Optional<BookingEntity> findByEventIdAndUserId(String eventId, String userId);
}
