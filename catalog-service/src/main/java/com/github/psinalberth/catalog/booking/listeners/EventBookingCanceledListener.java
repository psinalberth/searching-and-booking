package com.github.psinalberth.catalog.booking.listeners;

import com.github.psinalberth.catalog.booking.dtos.BookingEvent;
import com.github.psinalberth.catalog.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventBookingCanceledListener {

    private final BookingService bookingService;

    @KafkaListener(
            topics = "${app.config.message.event-booking-cancellation}",
            containerFactory = "containerFactory"
    )
    public void onBookingCanceled(final BookingEvent bookingCanceled) {
        log.info("Booking cancellation received for event {}", bookingCanceled.eventId());
        bookingService.processBookingCancellation(bookingCanceled);
    }
}
