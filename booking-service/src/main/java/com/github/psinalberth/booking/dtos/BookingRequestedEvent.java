package com.github.psinalberth.booking.dtos;

import com.github.psinalberth.booking.entities.Outbox;
import org.springframework.context.ApplicationEvent;

public class BookingRequestedEvent extends ApplicationEvent {

    private final Outbox outbox;

    public BookingRequestedEvent(Outbox outbox) {
        super(outbox);
        this.outbox = outbox;
    }

    public Outbox outbox() {
        return outbox;
    }
}
