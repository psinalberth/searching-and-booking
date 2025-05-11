package com.github.psinalberth.notification.repository;

import com.github.psinalberth.notification.dtos.NotificationTemplateDto;
import com.github.psinalberth.notification.entities.NotificationTemplateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationTemplateMapper {

    NotificationTemplateDto toDto(final NotificationTemplateEntity entity);
}
