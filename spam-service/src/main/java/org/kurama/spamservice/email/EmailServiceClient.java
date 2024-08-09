package org.kurama.spamservice.email;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "emailService", url = "${email-service.url}")
public interface EmailServiceClient {
    @GetMapping("/email")
    List<EmailDTO> getAllEmails();

}
