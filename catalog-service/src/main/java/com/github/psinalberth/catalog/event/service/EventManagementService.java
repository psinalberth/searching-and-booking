package com.github.psinalberth.catalog.event.service;

import com.github.psinalberth.catalog.event.dtos.CreateEventDto;
import com.github.psinalberth.catalog.event.dtos.EventDto;
import com.github.psinalberth.catalog.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventManagementService {

    private final EventRepository eventRepository;
    private final EventIndexProvider indexServiceProvider;

    @Transactional
    public EventDto save(final CreateEventDto eventDto) {
        log.info("Creating event {}", eventDto);
        var event = eventRepository.save(eventDto);
        indexServiceProvider.index(event);
        return event;
    }

    @Transactional
    public void confirmBooking(final String eventId) {
        log.info("Confirming booking index for event {}", eventId);
        indexServiceProvider.confirmBooking(eventId);
    }

    @Transactional
    public void cancelBooking(final String eventId) {
        log.info("Cancelling booking index for event {}", eventId);
        indexServiceProvider.cancelBooking(eventId);
    }
}
