package com.github.psinalberth.booking.controllers;

import com.github.psinalberth.booking.dtos.BookingCreationResponseDto;
import com.github.psinalberth.booking.dtos.CreateBookingDto;
import com.github.psinalberth.booking.service.BookingCancellationService;
import com.github.psinalberth.booking.service.BookingQueue;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingQueue bookingQueue;
    private final BookingCancellationService bookingService;

    @PostMapping
    public ResponseEntity<BookingCreationResponseDto> bookEvent(final @Valid @RequestBody CreateBookingDto bookingDto) {
        var bookingId = UUID.randomUUID().toString();
        var locationUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookingId)
                .toUri()
                .toString();
        var bookingResponse = new BookingCreationResponseDto(bookingId, locationUri, LocalDateTime.now());

        bookingQueue.enqueue(bookingDto.withBookingId(bookingId));

        return ResponseEntity.accepted()
                .header(HttpHeaders.LOCATION, locationUri)
                .body(bookingResponse);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(final @PathVariable String bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
