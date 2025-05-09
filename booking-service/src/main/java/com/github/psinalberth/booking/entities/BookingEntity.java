package com.github.psinalberth.booking.entities;

import com.github.psinalberth.booking.enums.BookingStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;

@Document("bookings")
public record BookingEntity(
        @Id
        @Field(value = "_id", targetType = FieldType.STRING)
        String id,

        @Field(value = "user_id")
        String userId,

        @Field(value = "event_id")
        String eventId,

        @Field(value = "status")
        BookingStatus status,

        @Field("created_at")
        LocalDateTime createdAt,

        @Field("updated_at")
        LocalDateTime updatedAt
) {
}
