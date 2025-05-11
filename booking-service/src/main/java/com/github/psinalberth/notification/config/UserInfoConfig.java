package com.github.psinalberth.notification.config;

import com.github.psinalberth.notification.dtos.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserInfoConfig {

    @Bean
    public UserInfo userInfo(
            @Value("${app.config.user-info.email}") String email,
            @Value("${app.config.user-info.phone}") String phone
    ) {
        return new UserInfo(email, phone);
    }
}
