INSERT INTO public.manager(
    first_name, last_name, status, created_at, updated_at)
VALUES ('Aron', 'Galpern', 'ACTIVE', '1998-07-07', null),
       ('Haim', 'Mizrahim', 'ACTIVE', '1998-07-07', '2023-11-10'),
       ('Samuil', 'Levi', 'ACTIVE', '1998-07-07', '2023-10-27'),
       ('Tavia', 'Vaiser', 'PENSION', '1998-07-07', null),
       ('Zavul', 'Izrail', 'BUSINESS_TRIP', '1998-07-07', '2023-10-27'),
       ('Abransam', 'Messolam', 'BUSINESS_TRIP', '1998-07-07', null),
       ('Larik', 'Valser', 'NOT_ACTIVE', '1998-07-07', '2023-10-27'),
       ('Lisak', 'dahan', 'ACTIVE', '1998-07-07', '2023-10-27'),
       ('Samuil', 'Izrail', 'PENSION', '1998-07-07', null);

INSERT INTO public.client(
    first_name, last_name, address, tax_code,  email, phone, status, created_at, updated_at, manager_id)
VALUES ('Ivan', 'Pohomov', 'StariyOskol', 'VIP', 'Ivan@mail.ru', '(123)123-45-67', 'ACTIVE', '1998-07-07', null, 1),
       ('Artem', 'Nesterenko', 'Gubkin', 'VIP', 'Artem@mail.ru', '(123)123-45-68', 'ACTIVE', '1998-07-07', '2023-11-10', 2),
       ('Semen', 'Shevtsov', 'Gubkin', 'VIP', 'Semen@list.ru', '(123)123-45-69', 'ACTIVE', '1998-07-07', '2023-10-27', 1),
       ('Arhip', 'Kalashnikov', 'Gubkin', 'VIP', 'Arhip@gmail.comu', '(123)123-45-70', 'PENDING', '1998-07-07', null, 3),
       ('Bogdan', 'Noshevkin', 'Belgorod', 'VIP', 'Bogdan@mail.ru', '(123)123-45-71', 'BLACKLISTED', '1998-07-07', '2023-10-27', 5),
       ('Stepan', 'Bogdanov', 'Gubkin', 'VIP', 'Stepan@gmail.com', '(123)123-45-72', 'BLACKLISTED', '1998-07-07', null, 2),
       ('Egor', 'Churikov', 'Belgorod', 'VIP', 'Egor@mail.ru', '(123)123-45-73', 'INACTIVE', '1998-07-07', '2023-10-27', 2),
       ('Vlalimir', 'Putin', 'Moskva', 'VIP', 'Vlalimir@mail.ru', '(123)123-45-74', 'ACTIVE', '1998-07-07', '2023-10-27', 1),
       ('Sergey', 'Voronov', 'Belgorod', 'VIP', 'Sergey@mail.ru', '(123)123-45-75', 'PENDING', '1998-07-07', null, 1);


INSERT INTO public.product(
    name, status, currency_code, interest_rate,  product_limit, created_at, updated_at, manager_id)
VALUES ('deposit high', 'ACTIVE', 'RUB', 17.25, 100000.00, '1998-07-07', null,1),
       ('deposit high', 'ACTIVE', 'USD', 5.75, 10000, '1998-07-07', null, 2),
       ('deposit high', 'ACTIVE', 'EUR',  7.50,  120000, '1998-07-07', null, 3),
       ('mass deposit', 'ACTIVE', 'RUB', 17.25, 100000.00, '1998-07-07', '2023-11-10',1),
       ('mass deposit', 'ACTIVE', 'USD', 5.75, 10000, '1998-07-07', '2023-11-10',2),
       ('mass deposit', 'ACTIVE', 'EUR',  7.50,  120000, '1998-07-07', '2023-11-10',3),
       ('short-term deposit','ACTIVE', 'RUB', 17.25, 100000.00,  '1998-07-07', '2023-10-27',1),
       ('short-term deposit','ACTIVE', 'USD', 5.75, 10000,  '1998-07-07', '2023-10-27', 2),
       ('short-term deposit','ACTIVE', 'EUR',  7.50,  120000, '1998-07-07', '2023-10-27', 3),
       ('average deposit', 'ACTIVE', 'RUB', 17.25, 100000.00,  '1998-07-07', null, 1),
       ('average deposit', 'ACTIVE', 'USD', 5.75, 10000,  '1998-07-07', null, 2),
       ('average deposit', 'ACTIVE', 'EUR',  7.50,  120000,  '1998-07-07', null, 3),
       ('investment deposit', 'ACTIVE', 'RUB', 17.25, 100000.00,  '1998-07-07', '2023-10-27', 1),
       ('investment deposit', 'ACTIVE', 'USD', 5.75, 10000,  '1998-07-07', '2023-10-27' ,2),
       ('investment deposit', 'ACTIVE', 'EUR',  7.50,  120000,  '1998-07-07', '2023-10-27', 3),
       ('short credit', 'ACTIVE', 'RUB', 17.25, 100000.00,  '1998-07-07', null, 1),
       ('short credit', 'ACTIVE', 'USD', 5.75, 10000,  '1998-07-07', null, 2),
       ('short credit', 'ACTIVE', 'EUR',  7.50,  120000,  '1998-07-07', null, 3),
       ('average credit', 'ACTIVE', 'RUB', 17.25, 100000.00,  '1998-07-07', '2023-10-27', 1),
       ('average credit', 'ACTIVE', 'USD', 5.75, 10000,  '1998-07-07', '2023-10-27', 2),
       ('average credit', 'ACTIVE', 'EUR',  7.50,  120000,  '1998-07-07', '2023-10-27', 3),
       ('long-term loan', 'ACTIVE', 'RUB', 17.25, 100000.00, '1998-07-07', '2023-10-27', 1),
       ('long-term loan', 'ACTIVE', 'USD', 5.75, 10000, '1998-07-07', '2023-10-27', 2),
       ('long-term loan', 'ACTIVE', 'EUR',  7.50,  120000, '1998-07-07', '2023-10-27', 3),
       ('investment loan', 'ACTIVE', 'RUB', 17.25, 100000.00,  '1998-07-07', '2023-10-27', 1),
       ('investment loan', 'ACTIVE', 'USD', 5.75, 10000,  '1998-07-07', '2023-10-27', 2),
       ('investment loan', 'ACTIVE', 'EUR',  7.50,  120000,  '1998-07-07', '2023-10-27', 3),
       ('secured loan', 'ACTIVE', 'RUB', 17.25, 100000.00,  '1998-07-07', '2023-10-27', 1),
       ('secured loan', 'ACTIVE', 'USD', 5.75, 10000, '1998-07-07', '2023-10-27', 2),
       ('secured loan', 'ACTIVE', 'EUR',  7.50,  120000,  '1998-07-07', '2023-10-27', 3),
       ('real estate loan', 'ACTIVE', 'RUB', 17.25, 100000.00,  '1998-07-07', '2023-10-27', 1),
       ('real estate loan', 'ACTIVE', 'USD', 5.75, 10000,  '1998-07-07', '2023-10-27', 2),
       ('real estate loan', 'ACTIVE', 'EUR',  7.50,  120000,  '1998-07-07', '2023-10-27', 3);


INSERT INTO public.account(
    name, type, status, balance, currency_code, created_at, updated_at, client_id)
VALUES ('number1', 'IRA', 'ACTIVE', 2995645.56, 'RUB', '1998-07-07', null, 1),
       ('number2', 'IRA', 'ACTIVE', 567834.57, 'RUB', '1998-07-07', null, 1),
       ('number3', 'IRA', 'ACTIVE', 65463221365.58, 'RUB', '1998-07-07', null, 1),
       ('number4', 'IRA', 'ACTIVE', 6543202525.59, 'RUB', '1998-07-07', null, 2),
       ('number5', 'IRA', 'ACTIVE', 6542200.60, 'RUB', '1998-07-07', null, 3),
       ('number6', 'IRA', 'ACTIVE', 56712124.61, 'RUB', '1998-07-07', null, 4),
       ('number7', 'IRA', 'ACTIVE', 324512.62, 'RUB', '1998-07-07', null, 5),
       ('number8', 'IRA', 'ACTIVE', 9688555.63, 'RUB', '1998-07-07', null, 6),
       ('number9', 'IRA', 'ACTIVE', 8543.64, 'RUB', '1998-07-07', null, 7),
       ('number10', 'IRA', 'ACTIVE', 100000.00, 'RUB', '1998-07-07', null, 8),
       ('number11', 'IRA', 'ACTIVE', 100000.00, 'RUB', '1998-07-07', null, 9);



INSERT INTO public.transaction(type, amount, description,debit_account_id, credit_account_id,created_at)
VALUES (   'DEPOSIT', 100000, 'Перевод клиентом 9 со счета number11  клиенту 8 на счет number11', 9, 8, '2023-11-13');



INSERT INTO public.agreement( interest_rate, status, sum, created_at, updated_at, product_id, account_id)
VALUES (   17.25, 'ACTIVE', 100000,  '1998-07-07', null,1, 1);




