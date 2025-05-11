package com.github.psinalberth.notification.service;

import com.github.psinalberth.notification.dtos.NotificationTemplateDto;
import com.github.psinalberth.notification.repository.NotificationTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationTemplateService {

    private final NotificationTemplateRepository repository;

    public NotificationTemplateDto findById(final String templateId) {
        return repository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found with id: " + templateId));
    }
}
