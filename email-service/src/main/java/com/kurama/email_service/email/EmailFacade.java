package com.kurama.email_service.email;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailFacade {

    @NonNull
    private final EmailService service;

    @NonNull
    private final EmailMapper mapper;

    public Page<EmailDTO> findAll(Pageable pageable) {
        return mapper.mapPage(service.findAll(pageable));
    }

    public EmailDTO findById(@NonNull Long emailId) {
        return mapper.emailToEmailDTO(service.findById(emailId));
    }

    public List<EmailDTO> create(List<EmailDTO> emailDTOs) {
        return mapper.mapEmailListToEmailDTOList(service.create(mapper.mapEmailDTOListToEmailList(emailDTOs)));
    }

    public EmailDTO delete(@NonNull Long emailId) {
        return mapper.emailToEmailDTO(service.delete(emailId));
    }

    public EmailDTO update(@NonNull Long emailId, EmailDTO emailDTO) {
        return mapper.emailToEmailDTO(service.update(emailId, mapper.emailDTOToEmail(emailDTO)));
    }

    public List<EmailDTO> deleteAllById(List<Long> emailIds) {
        return mapper.mapEmailListToEmailDTOList(service.deleteAllById(emailIds));
    }
}
