package com.github.psinalberth.catalog.event.service;

import com.github.psinalberth.catalog.event.dtos.EventDto;
import com.github.psinalberth.catalog.event.dtos.SearchEventDto;
import org.springframework.data.web.PagedModel;

public interface EventSearchProvider {

    PagedModel<EventDto> searchEvents(final SearchEventDto search);

    EventDto searchById(final String eventId);
}
