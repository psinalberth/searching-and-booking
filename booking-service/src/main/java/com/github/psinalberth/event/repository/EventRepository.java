package com.github.psinalberth.event.repository;

import com.github.psinalberth.event.dtos.EventDto;
import com.github.psinalberth.event.entities.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventRepository {

    private final EventDatabaseRepository delegate;
    private final EventMapper eventMapper;

    @Transactional
    public void saveOrUpdate(final EventDto event) {
        delegate.save(eventMapper.toEntity(event));
    }

    public Optional<EventDto> findById(final String eventId) {
        return delegate.findById(eventId)
                .map(eventMapper::toDto);
    }
}
