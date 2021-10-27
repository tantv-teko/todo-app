--liquibase formatted sql

--changeset chiennv:2

INSERT INTO `notes` (`title`, `content`)
VALUES ('t1', 'aaa');
INSERT INTO `notes` (`title`, `content`)
VALUES ('t2', 'bbb');