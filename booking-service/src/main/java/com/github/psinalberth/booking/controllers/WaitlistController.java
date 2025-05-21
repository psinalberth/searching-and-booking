package com.github.psinalberth.booking.controllers;

import com.github.psinalberth.booking.dtos.CreateWaitlistDto;
import com.github.psinalberth.booking.dtos.CreateWaitlistRequest;
import com.github.psinalberth.booking.service.WaitlistService;
import com.github.psinalberth.shared.dtos.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/waitlist")
@RequiredArgsConstructor
public class WaitlistController implements WaitlistControllerOpenApi {

    private final WaitlistService waitlistService;

    @PostMapping
    public ResponseEntity<Void> save(
            @RequestHeader("X-User-Id") final String userId,
            @RequestHeader("X-User-Email") final String userEmail,
            @RequestHeader("X-User-Phone") final String userPhone,
            @RequestBody CreateWaitlistRequest request
    ) {
        waitlistService.save(new CreateWaitlistDto(request.eventId(), new UserInfo(userId, userEmail, userPhone)));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> delete(
            @RequestHeader("X-User-Id") final String userId,
            @PathVariable String eventId
    ) {
        waitlistService.remove(userId, eventId);
        return ResponseEntity.noContent().build();
    }
}
