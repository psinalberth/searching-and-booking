package com.github.psinalberth.notification.shared.providers.twilio;

import com.github.psinalberth.notification.dtos.NotificationDto;
import com.github.psinalberth.notification.service.NotificationProvider;
import com.github.psinalberth.notification.service.NotificationTemplateService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class SendGridEmailNotificationService implements NotificationProvider {

    private final SendGrid sendGrid;
    private final NotificationTemplateService templateService;
    private final String mailAccount;

    public SendGridEmailNotificationService(
            final SendGrid sendGrid,
            final NotificationTemplateService templateService,
            @Value("${app.config.mail.account}") final String mailAccount
    ) {
        this.sendGrid = sendGrid;
        this.templateService = templateService;
        this.mailAccount = mailAccount;
    }

    @Override
    public void send(final NotificationDto notification) {
        var from = new Email(mailAccount);
        var to = new Email(notification.userInfo().email());
        var template = templateService.findById(notification.templateId());
        var content = new Content("text/plain", template.mailBody());
        var mail = new Mail(from, template.title(), to, content);

        try {
            var request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sendGrid.api(request);

            log.info("Email sent to {}", notification.userInfo().email());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
