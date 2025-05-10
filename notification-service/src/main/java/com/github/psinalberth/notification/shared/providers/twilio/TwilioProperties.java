package com.github.psinalberth.notification.shared.providers.twilio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "app.config.sms.twilio")
public class TwilioProperties {

    private String accountSid;
    private String authToken;
    private String phoneNumber;
}
