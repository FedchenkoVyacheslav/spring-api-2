insert into user (id, email, password, name, surname, age, location, created_at, updated_at, active)
values (2, 'bs@gmail.com', '$2a$12$CS/36EDoW1EzCTmFZCJ0Rem0Eyr/qOzUkWOlPngqgYOFnpLbR8vLm', 'Bob', 'Smith', 45,
        'New-York', '2023-06-12 17:39:22.560000', '2023-07-23 17:40:23.560000', true),
       (3, 'jm@gmail.com', '$2a$12$CS/36EDoW1EzCTmFZCJ0Rem0Eyr/qOzUkWOlPngqgYOFnpLbR8vLm', 'Joe', 'Martin', 25,
        'London', '2023-08-12 12:54:44.560000', '2023-08-12 12:54:44.560000', true);

insert into user_role (user_id, roles)
values (2, 'USER'),
       (3, 'USER');