package com.kurama.email_service.email;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface EmailMapper {

    EmailMapper INSTANCE = Mappers.getMapper(EmailMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "emailId"),
            @Mapping(source = "sender", target = "emailFrom"),
            @Mapping(source = "to", target = "emailTo"),
            @Mapping(source = "cc", target = "emailCC"),
            @Mapping(source = "body", target = "emailBody"),
            @Mapping(source = "state", target = "state")
    })
    EmailDTO emailToEmailDTO(Email email);

    default List<EmailDTO.Destination> mapToListOfDestinations(List<String> emails) {
        return emails != null ? emails.stream()
                .map(EmailDTO.Destination::new)
                .collect(Collectors.toList()) : null;
    }

    default List<String> mapToListOfStrings(List<EmailDTO.Destination> destinations) {
        return destinations != null ? destinations.stream()
                .map(EmailDTO.Destination::email)
                .collect(Collectors.toList()) : null;
    }

    default String mapState(Email.State state) {
        return state != null ? state.name() : null;
    }

    default Page<EmailDTO> mapPage(Page<Email> emailPage) {
        List<EmailDTO> emailDTOs = emailPage.getContent().stream()
                .map(this::emailToEmailDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(emailDTOs, emailPage.getPageable(), emailPage.getTotalElements());
    }
}
