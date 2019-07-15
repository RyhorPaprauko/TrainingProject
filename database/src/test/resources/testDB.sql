INSERT INTO bookstore_storage.author (id, name, surname, bio)
VALUES (1, 'Alexander', 'Pushkin', 'Molodec'),
       (2, 'Segrey', 'Esenin', 'Molodec');

INSERT INTO bookstore_storage.book (id, name, about, img_link, genre, price)
VALUES (1, 'Anchar', 'about poison tree', 'images/anchar.jpg', 'CLASSIC', 25),
       (2, 'Onegin', 'about love', 'images/onegin.jpg', 'CLASSIC', 25);

INSERT INTO bookstore_storage.book_author (book_id, author_id)
VALUES (1, 1),
       (2, 1);

INSERT INTO bookstore_storage."user" (id, login, password, fullname, mail, tel)
VALUES (1, 'admin', 'yaAdmin', 'adminovich', 'admin@mail.ru', '+375(25)906-47-10'),
       (2, 'user', 'yaUser(', 'userovich', 'user@mail.ru', '+375(25)906-47-10');

INSERT INTO bookstore_storage.booking(id, user_id, is_completed, is_processed)
VALUES (1, 1, FALSE, FALSE);

INSERT INTO bookstore_storage.booking_book (booking_id, book_id)
VALUES (1, 1),
       (1, 2);

INSERT INTO bookstore_storage.comment(id, user_id, book_id, text)
VALUES (1, 1, 1, 'best book i ever read');

INSERT INTO bookstore_storage.role(id, role)
VALUES (1, 'ADMIN'),
       (2, 'USER');

INSERT INTO bookstore_storage.user_role(user_id, role_id)
VALUES (1,1),
       (2,2);