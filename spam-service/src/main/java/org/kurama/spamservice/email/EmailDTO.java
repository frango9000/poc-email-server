package org.kurama.spamservice.email;

import lombok.Data;

import java.util.List;

@Data
public class EmailDTO {

    public Long emailId;
    public String emailFrom;
    public List<Destination> emailTo;
    public List<Destination> emailCC;
    public String emailBody;
    public String state;

    public record Destination(String email) {
    }
}
