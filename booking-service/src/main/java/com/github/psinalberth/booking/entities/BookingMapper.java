package com.github.psinalberth.booking.entities;

import com.github.psinalberth.booking.dtos.BookingDto;
import com.github.psinalberth.booking.enums.BookingStatus;
import com.github.psinalberth.booking.dtos.CreateBookingDto;
import com.github.psinalberth.shared.dtos.UserInfo;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    default BookingEntity toEntity(final CreateBookingDto bookingDto) {
        return new BookingEntity(
                bookingDto.bookingId(),
                toEntity(bookingDto.userInfo()),
                bookingDto.eventId(),
                BookingStatus.REQUESTED,
                LocalDateTime.now(),
                null
        );
    }

    BookingDto toDto(final BookingEntity entity);

    default UserInfoEntity toEntity(final UserInfo userInfo) {
        return new UserInfoEntity(
                userInfo.id(),
                userInfo.email(),
                userInfo.phoneNumber()
        );
    }

    UserInfo toDto(final UserInfoEntity entity);
}
