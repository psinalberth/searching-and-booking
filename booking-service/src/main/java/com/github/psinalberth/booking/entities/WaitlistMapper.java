package com.github.psinalberth.booking.entities;

import com.github.psinalberth.booking.dtos.CreateWaitlistDto;
import com.github.psinalberth.booking.dtos.WaitlistDto;
import com.github.psinalberth.shared.dtos.UserInfo;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface WaitlistMapper {

    default WaitlistEntity toEntity(final CreateWaitlistDto waitlistDto) {
        return new WaitlistEntity(
                new WaitlistId(waitlistDto.userInfo().id(), waitlistDto.eventId()),
                toEntity(waitlistDto.userInfo()),
                LocalDateTime.now()
        );
    }

    default WaitlistDto toDto(final WaitlistEntity entity) {
        return new WaitlistDto(
                entity.id().eventId(),
                toDto(entity.user()),
                entity.createdAt()
        );
    }

    default UserInfoEntity toEntity(final UserInfo userInfo) {
        return new UserInfoEntity(
                userInfo.id(),
                userInfo.email(),
                userInfo.phoneNumber()
        );
    }

    UserInfo toDto(final UserInfoEntity entity);
}
