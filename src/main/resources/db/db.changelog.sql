--liquibase formatted sql

--changeset testauthor:testid2  

create table note (
    id int primary key AUTO_INCREMENT,
    title varchar(255),
    content varchar(255)
);