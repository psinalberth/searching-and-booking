package com.github.psinalberth.catalog.booking.publishers;

import com.github.psinalberth.catalog.booking.dtos.BookingEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookingEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendBookingEvent(final String topic, final BookingEvent bookingEvent) {
        log.info("Sending booking event with type '{}' for event {}", bookingEvent.type(), bookingEvent.eventId());
        kafkaTemplate.send(topic, bookingEvent.eventId(), bookingEvent);
    }
}
