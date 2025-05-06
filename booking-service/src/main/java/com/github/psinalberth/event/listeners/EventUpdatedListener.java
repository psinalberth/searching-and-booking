package com.github.psinalberth.event.listeners;

import com.github.psinalberth.event.dtos.EventDto;
import com.github.psinalberth.event.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventUpdatedListener {

    private final EventService eventService;

    @KafkaListener(
            topics = "${app.config.message.event-updated}",
            containerFactory = "containerFactory"
    )
    public void onEventUpdated(final EventDto eventUpdated) {
        log.info("Event updated received: {}", eventUpdated);
        eventService.saveOrUpdate(eventUpdated);
    }
}
