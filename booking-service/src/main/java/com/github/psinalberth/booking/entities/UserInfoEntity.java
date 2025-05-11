package com.github.psinalberth.booking.entities;

import org.springframework.data.mongodb.core.mapping.Field;

public record UserInfoEntity(

        @Field(value = "id")
        String id,

        @Field(value = "email")
        String email,

        @Field(value = "phone_number")
        String phoneNumber
) {
}
