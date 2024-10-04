DROP DATABASE TODO;
CREATE DATABASE IF NOT EXISTS TODO;

USE TODO;

CREATE TABLE IF NOT EXISTS USER
(
    user_id  INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name     VARCHAR(100)    NOT NULL,
    password VARCHAR(100)    NOT NULL,
    email    VARCHAR(100)    NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS TODO
(
    todo_id    INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    contents   VARCHAR(100)    NOT NULL,
    password   VARCHAR(100)    NOT NULL,
    created_at DATETIME        NOT NULL default now(),
    updated_at DATETIME        NOT NULL default now() on update now(),
    user_id    INT,
    FOREIGN KEY (user_id) REFERENCES USER (user_id) ON DELETE CASCADE
);
