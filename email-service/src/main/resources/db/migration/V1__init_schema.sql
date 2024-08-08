CREATE TABLE emails
(
    id     SERIAL PRIMARY KEY,
    sender VARCHAR(255) NOT NULL,
    body   TEXT,
    state  INT          NOT NULL
);

CREATE TABLE email_recipients_to
(
    email_id INTEGER      NOT NULL,
    email    VARCHAR(255) NOT NULL,
    FOREIGN KEY (email_id) REFERENCES emails (id) ON DELETE CASCADE
);

CREATE TABLE email_recipients_cc
(
    email_id INTEGER      NOT NULL,
    email    VARCHAR(255) NOT NULL,
    FOREIGN KEY (email_id) REFERENCES emails (id) ON DELETE CASCADE
);
