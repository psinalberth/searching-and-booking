package com.github.psinalberth.booking.service;

import com.github.psinalberth.booking.dtos.BookingDto;
import com.github.psinalberth.booking.dtos.BookingEvent;
import com.github.psinalberth.booking.dtos.BookingRequestedEvent;
import com.github.psinalberth.booking.dtos.CreateBookingDto;
import com.github.psinalberth.booking.enums.BookingEventType;
import com.github.psinalberth.booking.enums.BookingStatus;
import com.github.psinalberth.booking.repository.BookingRepository;
import com.github.psinalberth.event.repository.EventRepository;
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
    private final BookingNotificationService notificationService;

    @Transactional
    public void create(final CreateBookingDto bookingDto) {
        bookingRepository.findBy(bookingDto.eventId(), bookingDto.userId())
                .filter(BookingDto::isProcessed)
                .ifPresentOrElse(
                        booking -> {
                            log.info("Booking with id {} was already processed for event {}.", booking.id(), booking.eventId());
                            notificationService.notifyStatus(booking.id(), BookingEvent.of(BookingEventType.REQUEST, booking));
                        },
                        () -> {
                            eventRepository.findById(bookingDto.eventId())
                                    .ifPresentOrElse(event -> {
                                        if (event.isAvailableForSubscription()) {
                                            var booking = bookingRepository.save(bookingDto);
                                            var outbox = outboxRepository.save(OutboxType.BOOKING_REQUEST, booking);
                                            internalEventPublisher.publishEvent(new BookingRequestedEvent(outbox));
                                        } else {
                                            log.info("Event {} is not available for subscription. Rejecting booking request.", bookingDto.eventId());
                                            notificationService.notifyStatus(bookingDto.bookingId(), BookingEvent.of(bookingDto, BookingStatus.UNAVAILABLE_SPOTS));
                                        }
                                    }, () -> {
                                        log.info("Event {} not found. Rejecting booking request.", bookingDto.eventId());
                                        notificationService.notifyStatus(bookingDto.bookingId(), BookingEvent.of(bookingDto, BookingStatus.EVENT_NOT_AVAILABLE));
                                    });
                        });
    }
}
