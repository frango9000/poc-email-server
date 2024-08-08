package com.kurama.email_service.email;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailFacade {

    @NonNull
    private final EmailService service;


    @NonNull
    private final EmailMapper mapper;

    public Page<EmailDTO> getAll(Pageable pageable) {
        return mapper.mapPage(service.getAll(pageable));
    }
}
