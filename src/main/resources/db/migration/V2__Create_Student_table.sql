CREATE SEQUENCE hibernate_sequence start 1 increment 1;
CREATE TABLE IF NOT EXISTS student
(
    id   serial PRIMARY KEY NOT NULL,
    name TEXT UNIQUE        NOT NULL,
    mark TEXT               NOT NULL
);