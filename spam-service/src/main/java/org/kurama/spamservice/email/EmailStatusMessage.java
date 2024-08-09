package org.kurama.spamservice.email;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class EmailStatusMessage implements Serializable {
    private List<Long> emailIds;
    private String status;
}
