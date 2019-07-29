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

INSERT INTO bookstore_storage.book(name, about, img_link, genre, price)
VALUES ( 'Keepers of Kalachakra','Sanghi describes a world of people at war with one another—a boomeranging conflict of faiths that results in acts of such slow and planned human cruelty that they defy human imagination. Caught in the midst of this madness is Vijay Sundaram, a geek scientist who is only dimly aware that the wider sky outside his laboratory is stretched taut and close to being torn apart by forces that he wants simply to have nothing to do with.','/images/r1.jpg','CLASSIC',9),
       ( 'Fishers Queens Dynasty','Matsyagandha, Daseyi, Yojanagandha — the queen of Hastinapur, Satyavati. Abandoned as a baby, preyed on by a rishi, she hardens herself, determined that the next time she is with a man, she will be the one to win. And win she does: the throne of Hastinapur for herself, and the promise that her sons will be heirs to the kingdom. But at what cost?','/images/r2.jpg','COMEDY',19),
       ( 'Zero Sum','Returning to Tokyo in 1982 after a decade of mercenary work in the Philippines, a young John Rain learns that the killing business is now controlled by Victor, a half-Russian, half-Japanese sociopath who has ruthlessly eliminated all potential challengers. Victor gives Rain a choice: kill a government minister or die a grisly death. But the best route to the minister is through his gorgeous Italian wife, Maria, a route that puts Rain on a collision course not only with Victor but with the shadowy forces behind the Russian’s rise to dominance—and the longings of Rain’s own conflicted heart.','/images/r3.jpg','FANTASY',29),
       ( 'PS from Paris','On the big screen, Mia plays a woman in love. But in real life, she’s an actress in need of a break from her real-life philandering husband—the megastar who plays her romantic interest in the movies. So she heads across the English Channel to hide in Paris behind a new haircut, fake eyeglasses, and a waitressing job at her best friend’s restaurant.','/images/r4.jpg','CLASSIC',39),
       ( 'Trust Me Not','Rising corporate star Reeva Rai is offered a prestigious position in a top-notch PR agency. It is the opportunity of a lifetime. But working with Enigmatic Billionaire Kunaal Kabi was not going to be easy. Even as she develops feelings for him, she is determined to prove herself.','/images/r5.jpg','THRILLER',49);


INSERT INTO bookstore_storage.author(id, name, surname, bio)
VALUES (1,'Fedor','Dostoevskiy','1821-1881'),
       (2,'Jaroslav','Hasek','1883-1923');

INSERT INTO bookstore_storage.book_author(book_id, author_id)
VALUES (1, 1);