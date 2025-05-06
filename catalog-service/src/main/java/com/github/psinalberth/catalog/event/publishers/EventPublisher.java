package com.github.psinalberth.catalog.event.publishers;

import com.github.psinalberth.catalog.event.dtos.EventUpdatedDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topic;

    public EventPublisher(
            final KafkaTemplate<String, Object> kafkaTemplate,
            @Value("${app.config.message.event-updated}") final String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void sendEventUpdateEvent(final EventUpdatedDto eventUpdated) {
        log.info("Sending event update {}", eventUpdated);
        kafkaTemplate.send(topic, eventUpdated);
    }
}
