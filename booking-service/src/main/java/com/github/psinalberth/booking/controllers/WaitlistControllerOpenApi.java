package com.github.psinalberth.booking.controllers;

import com.github.psinalberth.booking.dtos.CreateWaitlistRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "Waitlist", description = "Waitlist operations")
public interface WaitlistControllerOpenApi {

    @Operation(
            summary = "Add user to event waitlist",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateWaitlistRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "204", description = "User added to event waitlist successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request"),
                    @ApiResponse(responseCode = "404", description = "Event not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    ResponseEntity<Void> save(
            @Parameter(example = "44567", in = ParameterIn.HEADER) final String userId,
            @Parameter(example = "user@domain.com", in = ParameterIn.HEADER) final String userEmail,
            @Parameter(example = "+1234567890123", in = ParameterIn.HEADER) final String userPhone,
            @RequestBody CreateWaitlistRequest request
    );


    @Operation(
            summary = "Removes user from event waitlist",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Waitlist cancelled successfully"),
                    @ApiResponse(responseCode = "404", description = "Event not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    ResponseEntity<Void> delete(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable String eventId
    );
}
