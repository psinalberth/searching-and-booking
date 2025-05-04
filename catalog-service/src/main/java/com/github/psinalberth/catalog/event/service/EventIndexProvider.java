package com.github.psinalberth.catalog.event.service;

import com.github.psinalberth.catalog.event.dtos.EventDto;

public interface EventIndexProvider {

    void index(final EventDto eventDto);

    void confirmBooking(final String eventId);

    void cancelBooking(final String eventId);
}
