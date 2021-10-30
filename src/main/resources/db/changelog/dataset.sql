--liquibase formatted sql

--changeset chiennv:2

INSERT INTO `notes` (`title`, `content`)
VALUES ('tttttttt1', 'aaa');
INSERT INTO `notes` (`title`, `content`)
VALUES ('tttttttt2', 'bbb');

--changeset chiennv:4
INSERT INTO `colors` (`name`, `code`, `is_default`)
VALUES ('red', '111', 0);
INSERT INTO `colors` (`name`, `code`, `is_default`)
VALUES ('orange', '222', 0);
INSERT INTO `colors` (`name`, `code`, `is_default`)
VALUES ('yellow', '333', 1);
INSERT INTO `colors` (`name`, `code`, `is_default`)
VALUES ('blue', '444', 0);
INSERT INTO `colors` (`name`, `code`, `is_default`)
VALUES ('green', '333', 0);