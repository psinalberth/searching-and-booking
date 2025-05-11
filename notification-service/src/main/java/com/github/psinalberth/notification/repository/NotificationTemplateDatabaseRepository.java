package com.github.psinalberth.notification.repository;

import com.github.psinalberth.notification.entities.NotificationTemplateEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTemplateDatabaseRepository extends MongoRepository<NotificationTemplateEntity, String> {
}
