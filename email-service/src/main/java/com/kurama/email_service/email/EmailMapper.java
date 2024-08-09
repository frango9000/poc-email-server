package com.kurama.email_service.email;

import org.kurama.email_domain.EmailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

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

    default String mapState(Email.State state) {
        return state != null ? state.name() : null;
    }

    @Mappings({
        @Mapping(source = "emailId", target = "id"),
        @Mapping(source = "emailFrom", target = "sender"),
        @Mapping(source = "emailTo", target = "to"),
        @Mapping(source = "emailCC", target = "cc"),
        @Mapping(source = "emailBody", target = "body"),
        @Mapping(source = "state", target = "state"),
        @Mapping(target = "lastUpdated", ignore = true)
    })
    Email emailDTOToEmail(EmailDTO emailDTO);

    default Email.State mapState(String state) {
        return state != null ? Email.State.valueOf(state) : null;
    }

    default List<String> mapToListOfStrings(List<EmailDTO.Destination> destinations) {
        return destinations != null ? destinations.stream()
            .map(EmailDTO.Destination::email)
            .collect(Collectors.toList()) : null;
    }

    default List<EmailDTO> mapEmailListToEmailDTOList(List<Email> emails) {
        return emails.stream()
            .map(this::emailToEmailDTO)
            .collect(Collectors.toList());
    }

    default List<Email> mapEmailDTOListToEmailList(List<EmailDTO> emailDTOs) {
        return emailDTOs.stream()
            .map(this::emailDTOToEmail)
            .collect(Collectors.toList());
    }
}
