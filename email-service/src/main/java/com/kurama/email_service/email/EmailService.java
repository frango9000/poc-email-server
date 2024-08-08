package com.kurama.email_service.email;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    @NonNull
    private final EmailRepository repository;

    public Page<Email> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
