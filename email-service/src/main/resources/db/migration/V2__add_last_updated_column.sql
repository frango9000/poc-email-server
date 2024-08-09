ALTER TABLE emails
    ADD COLUMN last_updated TIMESTAMP NOT NULL DEFAULT NOW();

UPDATE emails
SET last_updated = NOW();
