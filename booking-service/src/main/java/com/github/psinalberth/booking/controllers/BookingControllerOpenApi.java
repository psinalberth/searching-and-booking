package com.github.psinalberth.booking.controllers;

import com.github.psinalberth.booking.dtos.BookingCreationResponseDto;
import com.github.psinalberth.booking.dtos.CreateBookingRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Bookings", description = "Operations related to bookings")
public interface BookingControllerOpenApi {

    @Operation(
            summary = "Create a new booking",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateBookingRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Booking request accepted",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookingCreationResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    ResponseEntity<BookingCreationResponseDto> bookEvent(
            @Parameter(example = "44567") final String userId,
            final CreateBookingRequest request);

    @Operation(
            summary = "Cancel an existing booking",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Booking cancelled successfully"),
                    @ApiResponse(responseCode = "404", description = "Booking not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    ResponseEntity<Void> cancelBooking(final @Parameter(example = "98864681-0af2-4130-b631-931e42dae71e") String bookingId);
}