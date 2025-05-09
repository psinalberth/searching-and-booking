package com.github.psinalberth.booking.entities;

import com.github.psinalberth.booking.dtos.BookingDto;
import com.github.psinalberth.booking.enums.BookingStatus;
import com.github.psinalberth.booking.dtos.CreateBookingDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    default BookingEntity toEntity(final CreateBookingDto bookingDto) {
        return new BookingEntity(
                bookingDto.bookingId(),
                bookingDto.userId(),
                bookingDto.eventId(),
                BookingStatus.REQUESTED,
                bookingDto.date(),
                LocalDateTime.now(),
                null
        );
    }

    BookingDto toDto(final BookingEntity entity);
}
