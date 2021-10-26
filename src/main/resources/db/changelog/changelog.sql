--liquibase formatted sql

--changeset testauthor:testid2

DROP TABLE IF EXISTS note;
CREATE TABLE note (
    id int primary key AUTO_INCREMENT,
    title varchar(64),
    content varchar(255),
    color_id int,
    create_at DateTime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    edited_at DateTime NOT NULL DEFAULT CURRENT_TIMESTAMP
);