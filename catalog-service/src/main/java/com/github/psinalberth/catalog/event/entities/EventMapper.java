package com.github.psinalberth.catalog.event.entities;

import com.github.psinalberth.catalog.event.dtos.CreateEventDto;
import com.github.psinalberth.catalog.event.dtos.EventDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "id", expression = "java(generateId())")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "withAvailableSpots", ignore = true)
    EventEntity toEntity(final CreateEventDto eventDto);

    @Mapping(target = "name", source = "value")
    AmenityEntity toEntity(String value);

    EventDto toDto(final EventEntity entity);

    default String toDto(final AmenityEntity entity) {
        return entity.name();
    }

    default String generateId() {
        return UUID.randomUUID().toString();
    }
}
