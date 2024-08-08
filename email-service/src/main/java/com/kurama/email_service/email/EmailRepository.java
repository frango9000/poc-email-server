package com.kurama.email_service.email;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, String> {

    @NonNull
    Page<Email> findAll(@NonNull Pageable pageable);
}
