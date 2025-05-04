package com.github.psinalberth.booking.publishers;

import com.github.psinalberth.booking.dtos.BookingEventType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class BookingEventProviders {

    private final Set<BookingEventProperties> bookingEventProperties;

    public String getTopicForType(final BookingEventType bookingEventType) {
        return bookingEventProperties.stream().filter(it -> it.getType() == bookingEventType)
                .findFirst()
                .map(BookingEventProperties::getName)
                .orElseThrow();
    }
}
