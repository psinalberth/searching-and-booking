package com.github.psinalberth.catalog.event.providers.elasticsearch;

import com.github.psinalberth.catalog.event.dtos.EventDto;
import com.github.psinalberth.catalog.event.dtos.SearchEventDto;
import com.github.psinalberth.catalog.event.service.EventSearchProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ElasticsearchSearchProvider implements EventSearchProvider {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public PagedModel<EventDto> searchEvents(final SearchEventDto search) {

        var query = EventSearchQuery.queryAvailableEvents(search);
        var pageable = PageRequest.of(search.pageNumber() - 1, search.pageSize());
        var searchHits = elasticsearchOperations.search(
                NativeQuery.builder().withQuery(query).withPageable(pageable).build(),
                EventDto.class,
                IndexCoordinates.of(EventKeys.INDEX_NAME)
        );

        log.info("Searching events with criteria {}. Hits found: {}", search, searchHits.getTotalHits());

        var page = SearchHitSupport.searchPageFor(searchHits, pageable)
                .map(SearchHit::getContent);

        return new PagedModel<>(page);
    }

    @Override
    public EventDto searchById(final String eventId) {
        log.info("Searching event with id {}", eventId);
        return elasticsearchOperations.get(eventId, EventDto.class, IndexCoordinates.of(EventKeys.INDEX_NAME));
    }
}
