package com.github.psinalberth.booking.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document("waitlist")
public record WaitlistEntity(

        @Field(value = "_id")
        WaitlistId id,

        @Field(value = "user")
        UserInfoEntity user,

        @Field(value = "created_at")
        LocalDateTime createdAt
) {
}
