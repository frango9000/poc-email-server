package com.kurama.email_service.email;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.kurama.email_domain.EmailStatusMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.kurama.email_service.RabbitMQConfig.QUEUE_NAME;

@Component
@RequiredArgsConstructor
public class EmailStatusListener {

    @NonNull
    private final EmailService service;

    @RabbitListener(queues = QUEUE_NAME)
    public void handleEmailStatusMessage(@NonNull EmailStatusMessage message) {
        message.getEmailIds().forEach(emailId -> service.update(emailId, Email.builder().state(Email.State.valueOf(message.getStatus().toUpperCase())).build()));
    }
}
