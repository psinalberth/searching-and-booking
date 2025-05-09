package com.github.psinalberth.booking.service;

import com.github.psinalberth.booking.dtos.BookingEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingNotificationService {

    private final SimpMessageSendingOperations messageSendingOperations;

    public void notifyStatus(final String bookingId, final BookingEvent event) {
        log.info("Sending booking notification to user '{}'. Event = {}", event.user(), event.status());
        messageSendingOperations.convertAndSendToUser(event.user(), "/topic/booking-status/" + bookingId, event);
    }
}
