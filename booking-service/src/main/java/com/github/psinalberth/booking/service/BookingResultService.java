package com.github.psinalberth.booking.service;

import com.github.psinalberth.booking.dtos.BookingEvent;
import com.github.psinalberth.booking.repository.BookingRepository;
import com.github.psinalberth.notification.dtos.BookingNotificationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingResultService {

    private final BookingRepository bookingRepository;
    private final ApplicationEventPublisher internalEventPublisher;

    @Transactional
    public void processBookingResult(final BookingEvent bookingEvent) {
        bookingRepository.findBy(bookingEvent.eventId(), bookingEvent.user())
                .ifPresent(booking -> {
                    log.info("Updating event {} with status '{}'", booking.eventId(), bookingEvent.type());
                    bookingRepository.update(booking.id(), bookingEvent.status());
                    internalEventPublisher.publishEvent(new BookingNotificationEvent(booking.id(), bookingEvent, booking.user()));
                });

    }
}
