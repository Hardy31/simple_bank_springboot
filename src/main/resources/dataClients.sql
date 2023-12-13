INSERT INTO public.client(
    first_name, last_name, address, tax_code,  email, phone, status, created_at, updated_at, manager_id)
VALUES ('Ivan', 'Pohomov', 'StariyOskol', 'VIP', 'Ivan@mail.ru', '(123)123-45-67', 'ACTIV', '1998-07-07', null, 1),
       ('Artem', 'Nesterenko', 'Gubkin', 'VIP', 'Artem@mail.ru', '(123)123-45-68', 'ACTIV', '1998-07-07', '2023-11-10', 2),
       ('Semen', 'Shevtsov', 'Gubkin', 'VIP', 'Semen@list.ru', '(123)123-45-69', 'ACTIV', '1998-07-07', '2023-10-27', 1),
       ('Arhip', 'Kalashnikov', 'Gubkin', 'VIP', 'Arhip@gmail.comu', '(123)123-45-70', 'PENSION', '1998-07-07', null, 3),
       ('Bogdan', 'Noshevkin', 'Belgorod', 'VIP', 'Bogdan@mail.ru', '(123)123-45-71', 'BUSINESS_TRIP', '1998-07-07', '2023-10-27', 5),
       ('Stepan', 'Bogdanov', 'Gubkin', 'VIP', 'Stepan@gmail.com', '(123)123-45-72', 'BUSINESS_TRIP', '1998-07-07', null, 2),
       ('Egor', 'Churikov', 'Belgorod', 'VIP', 'Egor@mail.ru', '(123)123-45-73', 'NOT_ACTIVE', '1998-07-07', '2023-10-27', 2),
       ('Vlalimir', 'Putin', 'Moskva', 'VIP', 'Vlalimir@mail.ru', '(123)123-45-74', 'ACTIV', '1998-07-07', '2023-10-27', 1),
       ('Sergey', 'Voronov', 'Belgorod', 'VIP', 'Sergey@mail.ru', '(123)123-45-75', 'PENSION', '1998-07-07', null, 1);
