package com.kurama.email_service.email;

import java.util.List;

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
