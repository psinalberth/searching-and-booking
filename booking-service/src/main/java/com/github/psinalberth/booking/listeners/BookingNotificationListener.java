package com.github.psinalberth.booking.listeners;

import com.github.psinalberth.notification.dtos.BookingNotificationEvent;
import com.github.psinalberth.notification.service.NotificationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookingNotificationListener {

    private final Set<NotificationProvider> notificationProviders;

    @EventListener
    public void onBookingNotification(final BookingNotificationEvent event) {
        notificationProviders.forEach(provider -> {
            try {
                provider.sendNotification(event);
            } catch (Exception e) {
                log.error("Error sending booking notification: {}", e.getMessage());
            }
        });
    }
}
