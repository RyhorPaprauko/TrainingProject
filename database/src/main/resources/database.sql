CREATE DATABASE bookstore_repository;

CREATE SCHEMA bookstore_storage;

CREATE TABLE "user"
(
    id       BIGSERIAL PRIMARY KEY,
    login    VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(250) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    mail     VARCHAR(124) NOT NULL,
    tel      CHAR(17)     NOT NULL
);

CREATE TABLE role
(
    id   BIGSERIAL PRIMARY KEY,
    role VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_role
(
    user_id BIGINT NOT NULL REFERENCES "user" (id),
    role_id BIGINT NOT NULL REFERENCES role (id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE book
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(124) NOT NULL,
    about    text,
    img_link VARCHAR(256),
    genre    VARCHAR(64)  NOT NULL,
    price    INTEGER      NOT NULL
);

CREATE TABLE author
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    bio     text
);

CREATE TABLE "booking"
(
    id           BIGSERIAL PRIMARY KEY,
    user_id      BIGINT  NOT NULL REFERENCES "user" (id),
    is_completed BOOLEAN NOT NULL,
    is_processed BOOLEAN NOT NULL
);

CREATE TABLE comment
(
    id      BIGSERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES "user" (id),
    book_id INTEGER NOT NULL REFERENCES book (id),
    text    text    NOT NULL
);

CREATE TABLE booking_book
(
    booking_id BIGINT NOT NULL REFERENCES "booking" (id),
    book_id    BIGINT NOT NULL REFERENCES book (id),
    PRIMARY KEY (booking_id, book_id)
);

CREATE TABLE book_author
(
    book_id   BIGINT NOT NULL REFERENCES book (id),
    author_id BIGINT NOT NULL REFERENCES author (id),
    PRIMARY KEY (author_id, book_id)
);

INSERT INTO bookstore_storage.book(id, name, about, img_link, genre, price)
VALUES (1, 'Besy','XIX century','images/besy.jpg','CLASSIC',20),
       (2, 'Shveik','I World War','images/shveik.jpg','COMEDY',25);

INSERT INTO bookstore_storage.author(id, name, surname, bio)
VALUES (1,'Fedor','Dostoevskiy','1821-1881'),
       (2,'Jaroslav','Hasek','1883-1923');

INSERT INTO bookstore_storage.book_author(book_id, author_id)
VALUES (1, 1);