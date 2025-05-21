package com.github.psinalberth.booking.repository;

import com.github.psinalberth.booking.entities.WaitlistEntity;
import com.github.psinalberth.booking.entities.WaitlistId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitlistDatabaseRepository extends MongoRepository<WaitlistEntity, WaitlistId> {

}
