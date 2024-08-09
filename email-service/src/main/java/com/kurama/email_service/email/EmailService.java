package com.kurama.email_service.email;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    @NonNull
    private final EmailRepository repository;

    public Page<Email> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Email findById(@NonNull Long emailId) {
        return repository.findById(emailId).orElseThrow(() -> new IllegalArgumentException("Email not found"));
    }

    public List<Email> create(List<Email> emails) {
        emails.forEach(email -> {
            email.setId(null);
            if (CollectionUtils.isEmpty(email.getTo())) {
                throw new IllegalArgumentException("Email must have at least one recipient");
            }
            if (email.getState() != Email.State.SENT) {
                email.setState(Email.State.DRAFT);
            }
        });
        return repository.saveAll(emails);
    }

    public Email delete(@NonNull Long emailId) {
        Email email = repository.findById(emailId).orElseThrow(() -> new IllegalArgumentException("Email not found"));
        if (email.getState() == Email.State.DELETED) {
            return email;
        }
        email.setState(Email.State.DELETED);
        return repository.save(email);
    }

    public Email update(@NonNull Long emailId, Email email) {
        Email existingEmail = repository.findById(emailId).orElseThrow(() -> new IllegalArgumentException("Email not found"));
        if (existingEmail.getState() != Email.State.DRAFT) {
            throw new IllegalArgumentException("Cannot update a non draft email");
        }
        boolean changed = false;
        if (email.getBody() != null && !email.getBody().equals(existingEmail.getBody())) {
            existingEmail.setBody(email.getBody());
            changed = true;
        }
        if (email.getTo() != null) {
            existingEmail.setTo(email.getTo());
            changed = true;
        }
        if (email.getCc() != null) {
            existingEmail.setCc(email.getCc());
            changed = true;
        }
        if (email.getSender() != null && !email.getSender().equals(existingEmail.getSender())) {
            existingEmail.setSender(email.getSender());
            changed = true;
        }
        if (email.getState() != null && email.getState() != existingEmail.getState()) {
            existingEmail.setState(email.getState());
            changed = true;
        }
        return changed ? repository.save(existingEmail) : existingEmail;
    }

    public List<Email> deleteAllById(List<Long> emailIds) {
        List<Email> emails = repository.findAllById(emailIds);
        emails.forEach(email -> email.setState(Email.State.DELETED));
        return repository.saveAll(emails);
    }
}
