package com.github.psinalberth.catalog.event.entities;

import com.github.psinalberth.catalog.event.dtos.CreateEventDto;
import com.github.psinalberth.catalog.event.dtos.EventDto;
import com.github.psinalberth.catalog.event.enums.EventStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EventMapper {

    default EventEntity toEntity(final CreateEventDto eventDto) {
        return new EventEntity(
                UUID.randomUUID().toString(),
                eventDto.title(),
                eventDto.description(),
                EventStatus.SUBSCRIPTION_AVAILABLE,
                eventDto.amenities().stream().map(this::toEntity).toList(),
                eventDto.availableSpots(),
                eventDto.date(),
                eventDto.maxSubscriptionDate(),
                LocalDateTime.now()
        );
    }

    @Mapping(target = "name", source = "value")
    AmenityEntity toEntity(String value);

    EventDto toDto(final EventEntity entity);

    default String toDto(final AmenityEntity entity) {
        return entity.name();
    }
}
