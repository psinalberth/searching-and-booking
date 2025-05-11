package com.github.psinalberth.notification.dtos;

import com.github.psinalberth.booking.enums.BookingEventType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotificationTemplateProperties {

    private BookingEventType type;
    private String templateId;
}
