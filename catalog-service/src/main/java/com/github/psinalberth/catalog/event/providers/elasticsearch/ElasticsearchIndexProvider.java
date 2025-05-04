package com.github.psinalberth.catalog.event.providers.elasticsearch;

import com.github.psinalberth.catalog.event.dtos.EventDto;
import com.github.psinalberth.catalog.event.service.EventIndexProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ElasticsearchIndexProvider implements EventIndexProvider {

    private final ElasticsearchOperations elasticsearchOperations;


    @Override
    public void index(final EventDto eventDto) {
        log.info("Indexing event {}", eventDto);
        elasticsearchOperations.save(eventDto, IndexCoordinates.of(EventKeys.INDEX_NAME));
    }

    @Override
    public void confirmBooking(final String eventId) {
        log.info("Updating 'CONFIRMATION' to index for event {}", eventId);
        updateEvent(eventId, -1);
    }

    @Override
    public void cancelBooking(final String eventId) {
        log.info("Updating 'CANCELATION' to index for event {}", eventId);
        updateEvent(eventId, 1);
    }

    private void updateEvent(final String eventId, final Integer spotCount) {
        var event = elasticsearchOperations.get(eventId, EventDto.class, IndexCoordinates.of(EventKeys.INDEX_NAME));
        if (event != null) {
            var document = Document.create();
            document.put(EventKeys.AVAILABLE_SPOTS, event.availableSpots() + spotCount);

            var query = UpdateQuery.builder(eventId)
                    .withDocument(document)
                    .build();

            elasticsearchOperations.update(query, IndexCoordinates.of(EventKeys.INDEX_NAME));
        }
    }
}
