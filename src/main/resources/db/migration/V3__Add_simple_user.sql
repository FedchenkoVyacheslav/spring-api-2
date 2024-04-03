insert into user (id, email, password, name, surname, age, location, created_at, updated_at, photo_url, active)
values (2, 'jd@gmail.com', '$2a$12$CS/36EDoW1EzCTmFZCJ0Rem0Eyr/qOzUkWOlPngqgYOFnpLbR8vLm', 'Joi', 'Dream', 21,
        'New-York', '2019-06-12 17:39:22.560000', '2019-07-23 17:40:23.560000', 'user3.jpg', true),
       (3, 'ok@gmail.com', '$2a$12$CS/36EDoW1EzCTmFZCJ0Rem0Eyr/qOzUkWOlPngqgYOFnpLbR8vLm', 'Officer', 'K', 28,
        'London', '2019-08-12 12:54:44.560000', '2019-08-12 12:54:44.560000', 'user4.jpg', true),
       (4, 'rt@gmail.com', '$2a$12$CS/36EDoW1EzCTmFZCJ0Rem0Eyr/qOzUkWOlPngqgYOFnpLbR8vLm', 'Rachael', 'Taylor', 20,
        'Los Angeles', '2019-05-12 11:53:40.560000', '2019-05-12 15:55:45.560000', 'user1.jpg', true),
       (5, 'rd@gmail.com', '$2a$12$CS/36EDoW1EzCTmFZCJ0Rem0Eyr/qOzUkWOlPngqgYOFnpLbR8vLm', 'Rick', 'Deckard', 39,
        'Los Angeles', '2019-03-10 09:04:04.560000', '2019-03-11 10:24:14.560000', 'user2.jpg', true);

insert into user_role (user_id, roles)
values (2, 'USER'),
       (3, 'USER'),
       (4, 'USER'),
       (5, 'USER');