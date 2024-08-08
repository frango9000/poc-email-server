package com.kurama.email_service.email;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sender;

    @ElementCollection
    @CollectionTable(name = "email_recipients_to", joinColumns = @JoinColumn(name = "email_id"))
    @Column(name = "email")
    private List<String> to;

    @ElementCollection
    @CollectionTable(name = "email_recipients_cc", joinColumns = @JoinColumn(name = "email_id"))
    @Column(name = "email")
    private List<String> cc;

    @Lob
    private String body;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private State state;

    @Getter
    public enum State {
        SENT(0),
        DRAFT(1),
        DELETED(2),
        SPAM(3);

        private final int value;

        State(int value) {
            this.value = value;
        }

        public static State fromValue(int value) {
            for (State state : State.values()) {
                if (state.getValue() == value) {
                    return state;
                }
            }
            throw new IllegalArgumentException("Unknown value: " + value);
        }
    }
}
