package com.github.psinalberth.catalog.event.repository;

import com.github.psinalberth.catalog.event.dtos.CreateEventDto;
import com.github.psinalberth.catalog.event.dtos.EventDto;
import com.github.psinalberth.catalog.event.entities.EventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EventRepository {

    private final EventDatabaseRepository delegate;
    private final EventMapper eventMapper;

    @Transactional
    public EventDto save(final CreateEventDto eventDto) {
        var entity = delegate.save(eventMapper.toEntity(eventDto));
        return eventMapper.toDto(entity);
    }

    @Transactional
    public EventDto confirmBooking(final String eventId) {
        return update(eventId, 1);
    }

    @Transactional
    public EventDto cancelBooking(final String eventId) {
        return update(eventId, -1);
    }

    private EventDto update(final String id, final Integer spotCount) {
        return delegate.findById(id)
                .map(entity -> entity.withAvailableSpots(entity.availableSpots() + spotCount))
                .map(delegate::save)
                .map(eventMapper::toDto)
                .orElseThrow();
    }
}
