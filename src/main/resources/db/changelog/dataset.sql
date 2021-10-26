--liquibase formatted sql

--changeset chiennv:id03

INSERT INTO `note` (`id`, `title`, `content`, `color_id`, `create_at`, `edited_at`)
VALUES (NULL, 't1', 'aaa', '1', current_timestamp(), current_timestamp());
INSERT INTO `note` (`id`, `title`, `content`, `color_id`, `create_at`, `edited_at`)
VALUES (NULL, 't2', 'bbb', '1', current_timestamp(), current_timestamp());