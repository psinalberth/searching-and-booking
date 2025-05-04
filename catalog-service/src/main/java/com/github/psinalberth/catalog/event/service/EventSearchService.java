package com.github.psinalberth.catalog.event.service;

import com.github.psinalberth.catalog.event.dtos.EventDto;
import com.github.psinalberth.catalog.event.dtos.SearchEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventSearchService {

    private final EventSearchProvider searchProvider;

    public PagedModel<EventDto> searchEvents(final SearchEventDto searchEventDto) {
        log.info("Searching events by matching criteria {}", searchEventDto);
        return searchProvider.searchEvents(searchEventDto);
    }

    public EventDto searchById(final String eventId) {
        log.info("Searching event by id {}", eventId);
        return searchProvider.searchById(eventId);
    }
}
