package com.github.psinalberth.notification.repository;

import com.github.psinalberth.notification.dtos.NotificationTemplateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NotificationTemplateRepository {

    private final NotificationTemplateDatabaseRepository delegate;
    private final NotificationTemplateMapper mapper;

    public Optional<NotificationTemplateDto> findById(final String templateId) {
        log.info("Fetching notification template with id {}", templateId);
        return delegate.findById(templateId)
                .map(mapper::toDto);
    }
}
