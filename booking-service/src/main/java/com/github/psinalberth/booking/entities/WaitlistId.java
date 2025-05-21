package com.github.psinalberth.booking.entities;

import org.springframework.data.mongodb.core.mapping.Field;

public record WaitlistId(
        @Field(value = "user_id")
        String userId,

         @Field(value = "event_id")
         String eventId
) {
}
