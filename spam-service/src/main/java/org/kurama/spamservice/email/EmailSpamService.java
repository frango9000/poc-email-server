package org.kurama.spamservice.email;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.kurama.email_domain.EmailDTO;
import org.kurama.email_domain.EmailStatusMessage;
import org.kurama.spamservice.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class EmailSpamService {

    @NonNull
    private final EmailServiceClient emailServiceClient;

    @NonNull
    private final RabbitTemplate rabbitTemplate;

    //    @Scheduled(cron = "0 0 22 * * ?")
    @Scheduled(cron = "*/15 * * * * ?")
    public void logEmails() {
        try {
            List<EmailDTO> emails = emailServiceClient.getAllEmails();
            List<Long> spamIds = emails.stream().filter(email -> !email.getState().equals("SPAM") && getSpamEmails().contains(email.getEmailFrom())).map(email -> email.emailId).toList();
            if (!spamIds.isEmpty()) {
                sendSpamStatusMessage(EmailStatusMessage.builder().status("SPAM").emailIds(spamIds).build());
            } else {
                log.info("No spam emails found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getSpamEmails() {
        return List.of("email6@gmail.com");
    }


    public void sendSpamStatusMessage(EmailStatusMessage message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, message);
        System.out.println("Message sent to queue: " + message);
    }
}
