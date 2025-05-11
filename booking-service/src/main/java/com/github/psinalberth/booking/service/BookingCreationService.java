package com.github.psinalberth.booking.service;

import com.github.psinalberth.booking.dtos.BookingDto;
import com.github.psinalberth.booking.dtos.BookingEvent;
import com.github.psinalberth.booking.dtos.BookingRequestedEvent;
import com.github.psinalberth.booking.dtos.CreateBookingDto;
import com.github.psinalberth.booking.enums.BookingEventType;
import com.github.psinalberth.booking.enums.BookingStatus;
import com.github.psinalberth.booking.repository.BookingRepository;
import com.github.psinalberth.event.repository.EventRepository;
import com.github.psinalberth.notification.dtos.BookingNotificationEvent;
import com.github.psinalberth.outbox.enums.OutboxType;
import com.github.psinalberth.outbox.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingCreationService {

    private final BookingRepository bookingRepository;
    private final OutboxRepository outboxRepository;
    private final EventRepository eventRepository;
    private final ApplicationEventPublisher internalEventPublisher;

    @Transactional
    public void create(final CreateBookingDto bookingDto) {
        bookingRepository.findBy(bookingDto.eventId(), bookingDto.userInfo().id())
                .filter(BookingDto::isProcessed)
                .ifPresentOrElse(
                        booking -> {
                            log.info("Booking with id {} was already processed for event {}.", booking.id(), booking.eventId());
                            internalEventPublisher.publishEvent(new BookingNotificationEvent(
                                    booking.id(),
                                    BookingEvent.of(BookingEventType.CONFIRMATION, booking),
                                    bookingDto.userInfo())
                            );
                        },
                        () -> eventRepository.findById(bookingDto.eventId())
                                .ifPresentOrElse(event -> {
                                    if (event.isAvailableForSubscription()) {
                                        var booking = bookingRepository.save(bookingDto);
                                        var outbox = outboxRepository.save(OutboxType.BOOKING_REQUEST, booking);
                                        internalEventPublisher.publishEvent(new BookingRequestedEvent(outbox));
                                    } else {
                                        log.info("Event {} is not available for subscription. Rejecting booking request.", bookingDto.eventId());
                                        sendEventCancellation(bookingDto, BookingStatus.UNAVAILABLE_SPOTS);
                                    }
                                }, () -> {
                                    log.info("Event {} not found. Rejecting booking request.", bookingDto.eventId());
                                    sendEventCancellation(bookingDto, BookingStatus.EVENT_NOT_AVAILABLE);
                                }));
    }

    private void sendEventCancellation(final CreateBookingDto bookingDto, final BookingStatus status) {
        internalEventPublisher.publishEvent(new BookingNotificationEvent(
                bookingDto.bookingId(),
                BookingEvent.cancellation(bookingDto, status),
                bookingDto.userInfo())
        );
    }
}
