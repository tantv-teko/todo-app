--liquibase formatted sql

--changeset chiennv:1

DROP TABLE IF EXISTS notes;
CREATE TABLE notes (
    id int primary key AUTO_INCREMENT,
    title varchar(64),
    content varchar(255),
    create_at DateTime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    edited_at DateTime NOT NULL DEFAULT CURRENT_TIMESTAMP
);