package com.github.psinalberth.notification.dtos;

import com.github.psinalberth.booking.enums.BookingEventType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@ConfigurationProperties(prefix = "app.config.notification")
@Getter
@Setter
@NoArgsConstructor
public class NotificationProperties {

    private Set<NotificationTemplateProperties> templates;

    public NotificationTemplateProperties forType(final BookingEventType type) {
        return templates.stream()
                .filter(property -> property.getType() == type)
                .findFirst()
                .orElse(null);
    }
}
