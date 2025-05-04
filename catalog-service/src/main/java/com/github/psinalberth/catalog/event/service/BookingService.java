package com.github.psinalberth.catalog.event.service;

import com.github.psinalberth.catalog.event.dtos.BookingEvent;
import com.github.psinalberth.catalog.event.dtos.BookingStatus;
import com.github.psinalberth.catalog.event.publishers.BookingEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class BookingService {

    private final EventSearchService eventSearchService;
    private final EventManagementService eventManagementService;
    private final BookingEventPublisher bookingEventPublisher;
    private final String topicName;

    public BookingService(
            final EventSearchService eventSearchService,
            final EventManagementService eventManagementService,
            final BookingEventPublisher bookingEventPublisher,
            @Value("${app.config.message.event-booking-result}") final String topicName
    ) {
        this.eventSearchService = eventSearchService;
        this.eventManagementService = eventManagementService;
        this.bookingEventPublisher = bookingEventPublisher;
        this.topicName = topicName;
    }

    @Transactional
    public void processBookingRequest(final BookingEvent bookingEvent) {
        var event = eventSearchService.searchById(bookingEvent.eventId());
        var updatedBooking = bookingEvent.confirmation();

        if (event.hasAvailableSpots()) {
            eventManagementService.confirmBooking(event.id());
        } else {
            updatedBooking = bookingEvent.cancellation(BookingStatus.UNAVAILABLE_SPOTS);
            log.info("Event {} has no available spots at the moment. It is not possible to confirm booking",
                    bookingEvent.eventId());
        }

        bookingEventPublisher.sendBookingEvent(topicName, updatedBooking);
    }

    @Transactional
    public void processBookingCancellation(final BookingEvent bookingEvent) {
        var event = eventSearchService.searchById(bookingEvent.eventId());

        log.info("Proceeding with event cancellation indexing operation for event {}", event.id());

        eventManagementService.cancelBooking(event.id());
        bookingEventPublisher.sendBookingEvent(topicName, bookingEvent.cancellation(BookingStatus.USER_CANCELLATION));
    }
}
