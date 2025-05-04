package com.github.psinalberth.catalog.event.providers.elasticsearch;

import co.elastic.clients.elasticsearch._types.query_dsl.NumberRangeQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.github.psinalberth.catalog.event.dtos.SearchEventDto;

import static co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders.fuzzy;
import static co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders.match;
import static co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders.range;

public final class EventSearchQuery {

    public static Query queryAvailableEvents(final SearchEventDto searchEvent) {

        var availableSpotQuery = range(availableSpots ->
                availableSpots.number(NumberRangeQuery.of(b -> b.field(EventKeys.AVAILABLE_SPOTS).gt(0.0))));

        if (searchEvent.fulltextSearch() == null)
            return availableSpotQuery;

        return QueryBuilders.bool(builder -> builder
                .should(fuzzy(title -> title.field(EventKeys.TITLE).transpositions(true).value(searchEvent.fulltextSearch())))
                .should(fuzzy(description -> description.field(EventKeys.DESCRIPTION).value(searchEvent.fulltextSearch())))
                .should(match(amenities -> amenities.field(EventKeys.AMENITIES).query(searchEvent.fulltextSearch())))
                .filter(availableSpotQuery)
                .minimumShouldMatch("1")
        );
    }
}
