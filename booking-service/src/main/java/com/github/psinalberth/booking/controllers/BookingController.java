package com.github.psinalberth.booking.controllers;

import com.github.psinalberth.booking.dtos.BookingCreationResponseDto;
import com.github.psinalberth.booking.dtos.CreateBookingRequest;
import com.github.psinalberth.booking.service.BookingCancellationService;
import com.github.psinalberth.booking.service.BookingQueue;
import com.github.psinalberth.shared.dtos.UserInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("v1/bookings")
@RequiredArgsConstructor
public class BookingController implements BookingControllerOpenApi {

    private final BookingQueue bookingQueue;
    private final BookingCancellationService bookingService;

    @PostMapping
    public ResponseEntity<BookingCreationResponseDto> bookEvent(
            @RequestHeader("X-User-Id") final String userId,
            @RequestHeader("X-User-Email") final String userEmail,
            @RequestHeader("X-User-Phone") final String userPhone,
            final @Valid @RequestBody CreateBookingRequest request
    ) {
        var bookingId = UUID.randomUUID().toString();
        var locationUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookingId)
                .toUri()
                .toString();

        bookingQueue.enqueue(request.toBookingDto(new UserInfo(userId, userEmail, userPhone), bookingId));

        return ResponseEntity.accepted()
                .header(HttpHeaders.LOCATION, locationUri)
                .body(new BookingCreationResponseDto(bookingId, locationUri, LocalDateTime.now()));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(final @PathVariable String bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
