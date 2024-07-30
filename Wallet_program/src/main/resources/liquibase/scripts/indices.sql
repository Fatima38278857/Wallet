-- liquibase formatted sql


-- changeset dlatyshev:1
CREATE TABLE wallet (
    id BIGINT PRIMARY KEY  NOT NULL,
    balance DECIMAL(10,2) NOT NULL,
    user_id BIGINT NOT NULL
);