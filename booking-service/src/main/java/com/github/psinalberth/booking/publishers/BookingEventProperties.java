package com.github.psinalberth.booking.publishers;

import com.github.psinalberth.booking.dtos.BookingEventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "app.config.message.booking-events")
public class BookingEventProperties {
    private String name;
    private BookingEventType type;
}
