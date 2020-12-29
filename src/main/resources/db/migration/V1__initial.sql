DROP SEQUENCE IF EXISTS hibernate_sequence;

CREATE SEQUENCE hibernate_sequence;

DROP TABLE IF EXISTS transactions;

CREATE TABLE transactions(
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    description VARCHAR(100) NOT NULL,
    extended_info VARCHAR(100),
    amount DECIMAL NOT NULL,
    type VARCHAR(15) NOT NULL
);