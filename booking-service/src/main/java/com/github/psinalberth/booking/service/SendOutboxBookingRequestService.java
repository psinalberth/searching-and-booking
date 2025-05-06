package com.github.psinalberth.booking.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.psinalberth.booking.dtos.BookingDto;
import com.github.psinalberth.booking.dtos.BookingEvent;
import com.github.psinalberth.booking.dtos.BookingEventType;
import com.github.psinalberth.booking.dtos.OutboxStatus;
import com.github.psinalberth.booking.entities.Outbox;
import com.github.psinalberth.booking.publishers.BookingEventPublisher;
import com.github.psinalberth.booking.repository.OutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendOutboxBookingRequestService {

    private final BookingEventPublisher bookingEventPublisher;
    private final ObjectMapper objectMapper;
    private final OutboxRepository outboxRepository;
    private final String topic;

    public SendOutboxBookingRequestService(
            final BookingEventPublisher bookingEventPublisher,
            final ObjectMapper objectMapper,
            final OutboxRepository outboxRepository,
            @Value("${app.config.message.event-booking-requested}") final String topic
    ) {
        this.bookingEventPublisher = bookingEventPublisher;
        this.objectMapper = objectMapper;
        this.outboxRepository = outboxRepository;
        this.topic = topic;
    }

    public void sendBookingRequest(final Outbox outbox) {
        try {
            log.info("Processing outbox record with id {} for 'booking request'", outbox.id());
            var payload = objectMapper.readValue(outbox.payload(), BookingDto.class);
            var bookingEvent = new BookingEvent(payload.eventId(), payload.userId(), BookingEventType.REQUEST);
            bookingEventPublisher.sendBookingEvent(topic, bookingEvent);
            outboxRepository.updateStatus(outbox.id(), OutboxStatus.SENT);
        } catch (JsonProcessingException e) {
            log.error("Error during outbox payload parsing. Error = {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
