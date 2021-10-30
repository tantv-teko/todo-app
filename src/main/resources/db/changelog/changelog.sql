--liquibase formatted sql

--changeset chiennv:1

DROP TABLE IF EXISTS colors;
CREATE TABLE colors (
    id int primary key AUTO_INCREMENT,
    name varchar(64),
    code varchar(9),
    is_default tinyint(2)
);

DROP TABLE IF EXISTS notes;
CREATE TABLE notes (
    id int primary key AUTO_INCREMENT,
    title varchar(64),
    content varchar(255),
    color_id int,
    create_at DateTime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    edited_at DateTime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(color_id) REFERENCES colors(id) ON DELETE CASCADE ON UPDATE CASCADE
);