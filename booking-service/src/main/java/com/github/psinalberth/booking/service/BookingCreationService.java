package com.github.psinalberth.booking.service;

import com.github.psinalberth.booking.dtos.BookingDto;
import com.github.psinalberth.booking.dtos.BookingRequestedEvent;
import com.github.psinalberth.booking.dtos.CreateBookingDto;
import com.github.psinalberth.booking.repository.BookingRepository;
import com.github.psinalberth.booking.repository.OutboxRepository;
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
    private final ApplicationEventPublisher internalEventPublisher;

    @Transactional
    public void create(final CreateBookingDto bookingDto) {
        bookingRepository.findBy(bookingDto.eventId(), bookingDto.userId())
                .filter(BookingDto::isProcessed)
                .ifPresentOrElse(
                        booking -> log.info("Booking with id {} was already processed for event {}", booking.id(), booking.eventId()),
                        () -> {
                            var booking = bookingRepository.save(bookingDto);
                            var outbox = outboxRepository.save(booking);
                            internalEventPublisher.publishEvent(new BookingRequestedEvent(outbox));
                        });
    }
}
