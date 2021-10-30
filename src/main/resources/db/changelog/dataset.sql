--liquibase formatted sql

--changeset chiennv:2

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


--changeset chiennv:3

INSERT INTO `notes` (`title`, `content`, `color_id`)
VALUES ('tttttttt1', 'aaa', 1);
INSERT INTO `notes` (`title`, `content`, `color_id`)
VALUES ('tttttttt2', 'bbb', 1);