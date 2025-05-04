package com.github.psinalberth.booking.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.psinalberth.booking.dtos.BookingDto;
import com.github.psinalberth.booking.dtos.BookingEvent;
import com.github.psinalberth.booking.dtos.BookingEventType;
import com.github.psinalberth.booking.dtos.BookingRequestedEvent;
import com.github.psinalberth.booking.dtos.OutboxStatus;
import com.github.psinalberth.booking.publishers.BookingEventPublisher;
import com.github.psinalberth.booking.repository.OutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OutboxBookingRequestListener {

    private final BookingEventPublisher bookingEventPublisher;
    private final ObjectMapper objectMapper;
    private final OutboxRepository outboxRepository;
    private final String topic;

    public OutboxBookingRequestListener(
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

    @EventListener
    public void onBookingRequested(final BookingRequestedEvent event) {
        try {
            var payload = objectMapper.readValue(event.outbox().payload(), BookingDto.class);
            var bookingEvent = new BookingEvent(payload.eventId(), payload.userId(), BookingEventType.REQUEST);
            bookingEventPublisher.sendBookingEvent(topic, bookingEvent);
            outboxRepository.updateStatus(event.outbox().id(), OutboxStatus.SENT);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
