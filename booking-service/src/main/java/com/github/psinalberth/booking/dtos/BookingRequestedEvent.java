package com.github.psinalberth.booking.dtos;

import com.github.psinalberth.outbox.dtos.OutboxDto;

public record BookingRequestedEvent(OutboxDto outbox) {
}
