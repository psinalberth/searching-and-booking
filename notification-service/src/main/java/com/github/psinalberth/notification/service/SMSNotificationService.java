package com.github.psinalberth.notification.service;

import com.github.psinalberth.notification.dtos.NotificationDto;
import com.github.psinalberth.notification.shared.providers.twilio.TwilioProperties;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SMSNotificationService implements NotificationProvider {

    private final TwilioProperties twilioProperties;

    public SMSNotificationService(final TwilioProperties twilioProperties) {
        this.twilioProperties = twilioProperties;
        Twilio.init(twilioProperties.getAccountSid(), twilioProperties.getAuthToken());
    }

    @Override
    public void send(final NotificationDto notification) {
        var message = Message.creator(
                new PhoneNumber(notification.userInfo().phoneNumber()),
                new PhoneNumber(twilioProperties.getPhoneNumber()),
                notification.body()
        ).create();

        log.info("Sent SMS message {}", message);
    }
}
