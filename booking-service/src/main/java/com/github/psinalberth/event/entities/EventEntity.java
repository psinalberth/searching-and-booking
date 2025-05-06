package com.github.psinalberth.event.entities;

import com.github.psinalberth.event.dtos.EventStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;

@Document("events")
public record EventEntity(

        @Id
        @Field(value = "_id", targetType = FieldType.STRING)
        String id,

        @Field("title")
        String title,

        @Field("status")
        EventStatus status,

        @Field("date")
        LocalDateTime date,

        @Field("max_subscription_date")
        LocalDateTime maxSubscriptionDate,

        @Field("updated_at")
        LocalDateTime updatedAt
) {
}
