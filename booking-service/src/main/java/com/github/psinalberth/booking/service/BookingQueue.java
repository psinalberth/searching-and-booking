package com.github.psinalberth.booking.service;

import com.github.psinalberth.booking.dtos.CreateBookingDto;

public interface BookingQueue {

    void enqueue(final CreateBookingDto bookingDto);
}
