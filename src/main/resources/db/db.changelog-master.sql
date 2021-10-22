--liquibase formatted sql

--changeset testauthor:testid1 


CREATE TABLE label (
    id int primary key AUTO_INCREMENT,
    name varchar(64)
);

CREATE TABLE color (
    id int primary key AUTO_INCREMENT,
    name varchar(64),
    code varchar(9),
    is_default tinyint(2)
);

CREATE TABLE note (
    id int primary key AUTO_INCREMENT,
    title varchar(64),
    content varchar(255),
    color_id int,
    create_at DateTime,
    edited_at DateTime,
    FOREIGN KEY(color_id) REFERENCES color(id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE note_label (
    id int primary key AUTO_INCREMENT,
    label_id int,
    note_id int,
    UNIQUE(label_id, note_id),
    FOREIGN KEY (label_id) REFERENCES label(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (note_id) REFERENCES note(id)ON DELETE CASCADE ON UPDATE CASCADE
);
