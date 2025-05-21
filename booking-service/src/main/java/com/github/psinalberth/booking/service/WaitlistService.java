package com.github.psinalberth.booking.service;

import com.github.psinalberth.booking.dtos.CreateWaitlistDto;
import com.github.psinalberth.booking.dtos.WaitlistDto;
import com.github.psinalberth.booking.repository.WaitlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WaitlistService {

    private final WaitlistRepository waitlistRepository;

    public void save(final CreateWaitlistDto waitlistDto) {
        waitlistRepository.save(waitlistDto);
    }

    public Optional<WaitlistDto> findFirstWaitlistRegistry(final String eventId) {
        return waitlistRepository.findFirstWaitlistRegistry(eventId);
    }

    public void remove(final String userId, final String eventId) {
        waitlistRepository.remove(userId, eventId);
    }
}
