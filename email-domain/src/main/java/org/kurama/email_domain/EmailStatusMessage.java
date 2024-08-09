package org.kurama.email_domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class EmailStatusMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<Long> emailIds;
    private String status;
}
