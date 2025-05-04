package com.github.psinalberth.booking.listeners;

import com.github.psinalberth.booking.dtos.BookingEvent;
import com.github.psinalberth.booking.service.BookingResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookingResultListener {

    private final BookingResultService bookingService;

    @KafkaListener(
            topics = "${app.config.message.event-booking-result}",
            containerFactory = "containerFactory"
    )
    public void onBookingResult(final BookingEvent bookingEvent) {
        log.info("Received booking request result {}", bookingEvent);
        bookingService.processBookingResult(bookingEvent);
    }
}
