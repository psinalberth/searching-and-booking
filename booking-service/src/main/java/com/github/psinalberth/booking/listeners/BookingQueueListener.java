package com.github.psinalberth.booking.listeners;

import com.github.psinalberth.booking.dtos.CreateBookingDto;
import com.github.psinalberth.booking.service.BookingCreationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookingQueueListener {

    private final BookingCreationService bookingService;

    @KafkaListener(
            topics = "${app.config.message.event-booking-request-received}",
            containerFactory = "containerFactory"
    )
    public void onBookingRequest(final CreateBookingDto bookingDto) {
        log.info("Processing booking request for event {}", bookingDto.eventId());
        bookingService.create(bookingDto);
    }
}
