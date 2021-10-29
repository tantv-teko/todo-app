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

--changeset chiennv:3

DROP TABLE IF EXISTS colors;
CREATE TABLE colors (
    id int primary key AUTO_INCREMENT,
    name varchar(64),
    code varchar(9),
    is_default tinyint(2)
);