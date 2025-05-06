package com.github.psinalberth.booking.repository;

import com.github.psinalberth.booking.dtos.BookingDto;
import com.github.psinalberth.booking.enums.BookingStatus;
import com.github.psinalberth.booking.dtos.CreateBookingDto;
import com.github.psinalberth.booking.entities.BookingEntity;
import com.github.psinalberth.booking.entities.BookingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookingRepository {

    private final BookingDatabaseRepository delegate;
    private final MongoOperations mongoOperations;
    private final BookingMapper bookingMapper;

    @Transactional
    public BookingDto save(final CreateBookingDto bookingDto) {
        var entity = delegate.save(bookingMapper.toEntity(bookingDto));
        return bookingMapper.toDto(entity);
    }

    public Optional<BookingDto> findBy(final String eventId, final String userId) {
        return delegate.findByEventIdAndUserId(eventId, userId)
                .map(bookingMapper::toDto);
    }

    public Optional<BookingDto> findById(final String bookingId) {
        return delegate.findById(bookingId)
                .map(bookingMapper::toDto);
    }

    public void update(final String bookingId, final BookingStatus status) {
        var query = new Query(Criteria.where("_id").is(bookingId));
        var update = new Update()
                .set("status", status)
                .set("update_at", LocalDateTime.now());
        var execution = mongoOperations.updateFirst(query, update, BookingEntity.class);
        log.info("Updated booking {} with status {}. Modified count = {}", bookingId, status, execution.getModifiedCount());
    }
}
