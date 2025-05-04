package com.github.psinalberth.catalog.event.controllers;

import com.github.psinalberth.catalog.event.dtos.CreateEventDto;
import com.github.psinalberth.catalog.event.service.EventManagementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/events")
@RequiredArgsConstructor
public class EventManagementController {

    private final EventManagementService eventService;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody final CreateEventDto eventDto) {
        var event = eventService.save(eventDto);
        var uri = ResponseExtensions.toCreatedURI(event.id());
        return ResponseEntity.created(uri).build();
    }

}
