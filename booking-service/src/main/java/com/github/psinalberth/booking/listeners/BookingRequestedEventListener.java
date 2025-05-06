package com.github.psinalberth.booking.listeners;

import com.github.psinalberth.booking.dtos.BookingRequestedEvent;
import com.github.psinalberth.booking.service.SendBookingRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookingRequestedEventListener {

    private SendBookingRequestService outboxBookingRequestService;

    @EventListener
    public void onBookingRequested(final BookingRequestedEvent event) {
        log.info("Sending booking request from main booking flow");
        outboxBookingRequestService.sendBookingRequest(event.outbox());
    }
}
