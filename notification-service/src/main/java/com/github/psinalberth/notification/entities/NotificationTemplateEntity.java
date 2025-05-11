package com.github.psinalberth.notification.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;

@Document(collection = "notification_templates")
public record NotificationTemplateEntity(

        @Id
        @Field(value = "_id", targetType = FieldType.STRING)
        String id,

        @Field(value = "title")
        String title,

        @Field(value = "mail_body")
        String mailBody,

        @Field(value = "message_body")
        String messageBody,

        @Field(value = "created_at")
        LocalDateTime createdAt
) {
}
