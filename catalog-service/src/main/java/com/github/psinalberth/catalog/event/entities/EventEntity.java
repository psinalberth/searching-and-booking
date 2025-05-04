package com.github.psinalberth.catalog.event.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "events")
public record EventEntity(

    @Id
    @Field(value = "_id", targetType = FieldType.STRING)
    String id,

    @Field("title")
    String title,

    @Field("description")
    String description,

    @Field("amenities")
    List<AmenityEntity> amenities,

    @Field("available_places")
    Integer availableSpots,

    @Field("date")
    LocalDateTime date,

    @Field("created_at")
    LocalDateTime createdAt
) {

    public EventEntity withAvailableSpots(Integer availableSpots) {
        return new EventEntity(
                id(),
                title(),
                description(),
                amenities(),
                availableSpots,
                date(),
                createdAt()
        );
    }
}
