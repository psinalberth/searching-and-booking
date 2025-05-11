package com.github.psinalberth.booking.service;

import com.github.psinalberth.booking.dtos.BookingEvent;
import com.github.psinalberth.booking.enums.BookingEventType;
import com.github.psinalberth.booking.publishers.BookingEventPublisher;
import com.github.psinalberth.booking.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class BookingCancellationService {

    private final BookingRepository bookingRepository;
    private final BookingEventPublisher bookingEventPublisher;
    private final String topic;

    public BookingCancellationService(
            final BookingRepository bookingRepository,
            final BookingEventPublisher bookingEventPublisher,
            @Value("${app.config.message.event-booking-cancellation}") final String topic
    ) {
        this.bookingRepository = bookingRepository;
        this.bookingEventPublisher = bookingEventPublisher;
        this.topic = topic;
    }

    @Transactional
    public void cancelBooking(final String bookingId) {
        log.info("Received booking cancellation request for booking {}", bookingId);
        bookingRepository.findById(bookingId)
                .ifPresent(booking -> {
                    var event = new BookingEvent(booking.eventId(), booking.user().id(), BookingEventType.CANCELLATION);
                    bookingEventPublisher.sendBookingEvent(topic, event);
                });
    }
}
