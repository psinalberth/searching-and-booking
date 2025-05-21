package com.github.psinalberth.booking.repository;

import com.github.psinalberth.booking.dtos.CreateWaitlistDto;
import com.github.psinalberth.booking.dtos.WaitlistDto;
import com.github.psinalberth.booking.entities.WaitlistEntity;
import com.github.psinalberth.booking.entities.WaitlistId;
import com.github.psinalberth.booking.entities.WaitlistMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class WaitlistRepository {

    private final WaitlistDatabaseRepository delegate;
    private final MongoOperations mongoOperations;
    private final WaitlistMapper mapper;

    @Transactional
    public void save(final CreateWaitlistDto waitlistDto) {
        log.info("Saving waitlist registry {}", waitlistDto);
        var entity = mapper.toEntity(waitlistDto);
        delegate.save(entity);
    }

    public Optional<WaitlistDto> findFirstWaitlistRegistry(final String eventId) {
        log.info("Finding first waitlist registry for event {}", eventId);
        var query = new Query(Criteria.where("_id.event_id").is(eventId))
                .with(Sort.by(Sort.Direction.ASC, "created_at"))
                .limit(1);
        return mongoOperations.find(query, WaitlistEntity.class).stream()
                .findFirst()
                .map(mapper::toDto);
    }

    @Transactional
    public void remove(final String userId, final String eventId) {
        log.info("Removing waitlist registry for user {} and event {}", userId, eventId);
        var query = new Query(Criteria.where("_id").is(new WaitlistId(userId, eventId)));
        mongoOperations.findAndRemove(query, WaitlistEntity.class);
    }
}
