package com.github.psinalberth.catalog.event.controllers;

import com.github.psinalberth.catalog.event.dtos.EventDto;
import com.github.psinalberth.catalog.event.dtos.SearchEventDto;
import com.github.psinalberth.catalog.event.service.EventSearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/events")
@RequiredArgsConstructor
public class EventSearchController {

    private final EventSearchService searchService;

    @GetMapping
    public ResponseEntity<PagedModel<EventDto>> search(@Valid final SearchEventDto searchEventDto) {
        return ResponseEntity.ok(searchService.searchEvents(searchEventDto));
    }
}
