package com.github.psinalberth.event.service;

import com.github.psinalberth.event.dtos.EventDto;
import com.github.psinalberth.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional
    public void saveOrUpdate(final EventDto event) {
        log.info("Saving event: {}", event);
        eventRepository.saveOrUpdate(event);
    }
}
