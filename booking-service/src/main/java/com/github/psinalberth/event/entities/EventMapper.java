package com.github.psinalberth.event.entities;

import com.github.psinalberth.event.dtos.EventDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto toDto(final EventEntity entity);

    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    EventEntity toEntity(final EventDto event);
}
