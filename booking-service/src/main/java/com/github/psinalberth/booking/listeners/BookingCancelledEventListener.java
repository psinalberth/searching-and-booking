package com.github.psinalberth.booking.listeners;

import com.github.psinalberth.booking.dtos.BookingCancelledEvent;
import com.github.psinalberth.booking.dtos.CreateBookingDto;
import com.github.psinalberth.booking.service.BookingCreationService;
import com.github.psinalberth.booking.service.WaitlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookingCancelledEventListener {

    private final WaitlistService waitlistService;
    private final BookingCreationService bookingService;

    @EventListener
    public void onBookingCancelled(final BookingCancelledEvent event) {
        log.info("Checking for waitlist for event {}", event.eventId());
        waitlistService.findFirstWaitlistRegistry(event.eventId())
                .ifPresent(waitlist -> {
                    log.info("Creating booking request for user {} from waitlist", waitlist.user().id());
                    var newBookingDto = new CreateBookingDto(event.eventId(), waitlist.user(), UUID.randomUUID().toString());
                    bookingService.create(newBookingDto);
                    waitlistService.remove(waitlist.user().id(), waitlist.eventId());
                });
    }
}
