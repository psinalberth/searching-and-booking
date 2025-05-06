package com.github.psinalberth.booking.publishers;

import com.github.psinalberth.booking.dtos.CreateBookingDto;
import com.github.psinalberth.booking.service.BookingQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookingQueuePublisher implements BookingQueue {

    private final String topicName;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public BookingQueuePublisher(
            @Value("${app.config.message.event-booking-request-received}") String topicName,
            KafkaTemplate<String, Object> kafkaTemplate
    ) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void enqueue(final CreateBookingDto bookingDto) {
        log.info("Booking request received for event {}", bookingDto.eventId());
        kafkaTemplate.send(topicName, bookingDto.eventId(),bookingDto);
    }
}
