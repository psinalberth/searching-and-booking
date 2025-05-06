package com.github.psinalberth.booking.service;

import com.github.psinalberth.booking.dtos.OutboxType;
import com.github.psinalberth.booking.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookingRequestScheduler {

    private final SendOutboxBookingRequestService outboxBookingRequestService;
    private final OutboxRepository outboxRepository;

    @Scheduled(cron = "${app.config.scheduler.booking-request-scheduler-cron}")
    public void execute() {
        log.info("Starting 'booking request scheduler' execution");

        outboxRepository.findAllPendingByType(OutboxType.BOOKING_REQUEST)
                .forEach(outboxBookingRequestService::sendBookingRequest);

        log.info("Finished 'booking request scheduler' execution");
    }
}
