package com.github.psinalberth.notification.service;

import com.github.psinalberth.notification.dtos.BookingNotificationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InAppBookingNotificationService implements NotificationProvider {

    private final SimpMessageSendingOperations messageSendingOperations;

    @Override
    public void sendNotification(final BookingNotificationEvent notification) {
        log.info("Sending booking notification to user '{}'. Event = {}", notification.event().user(), notification.event().status());
        messageSendingOperations.convertAndSendToUser(notification.event().user(), "/topic/booking-status/" + notification.bookingId(), notification.event());
    }
}
