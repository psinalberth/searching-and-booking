package com.github.psinalberth.booking.controllers;

import com.github.psinalberth.booking.dtos.CreateBookingDto;
import com.github.psinalberth.booking.service.BookingCancellationService;
import com.github.psinalberth.booking.service.BookingQueue;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingQueue bookingQueue;
    private final BookingCancellationService bookingService;

    @PostMapping
    public ResponseEntity<Void> bookEvent(final @Valid @RequestBody CreateBookingDto bookingDto) {
        bookingQueue.enqueue(bookingDto);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(final @PathVariable String bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
