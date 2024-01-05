INSERT INTO public.manager(
    first_name, last_name, status, created_at, updated_at)
VALUES ('Aron', 'Galpern', 'ACTIV', '1998-07-07', null),
       ('Haim', 'Mizrahim', 'ACTIV', '1998-07-07', '2023-11-10'),
       ('Samuil', 'Levi', 'ACTIV', '1998-07-07', '2023-10-27'),
       ('Tavia', 'Vaiser', 'PENSION', '1998-07-07', null),
       ('Zavul', 'Izrail', 'BUSINESS_TRIP', '1998-07-07', '2023-10-27'),
       ('Abransam', 'Messolam', 'BUSINESS_TRIP', '1998-07-07', null),
       ('Larik', 'Valser', 'NOT_ACTIVE', '1998-07-07', '2023-10-27'),
       ('Lisak', 'dahan', 'ACTIV', '1998-07-07', '2023-10-27'),
       ('Samuil', 'Izrail', 'PENSION', '1998-07-07', null);

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


INSERT INTO public.product(
    name, status, currency_code, interest_rate,  product_limit, created_at, updated_at, manager_id)
VALUES ('deposit high', 'ACTIV', 'RUB', 17.25, 100000.00, '1998-07-07', null,1),
       ('deposit high', 'ACTIV', 'USD', 5.75, 10000, '1998-07-07', null, 2),
       ('deposit high', 'ACTIV', 'EUR',  7.50,  120000, '1998-07-07', null, 3),
       ('mass deposit', 'ACTIV', 'RUB', 17.25, 100000.00, '1998-07-07', '2023-11-10',1),
       ('mass deposit', 'ACTIV', 'USD', 5.75, 10000, '1998-07-07', '2023-11-10',2),
       ('mass deposit', 'ACTIV', 'EUR',  7.50,  120000, '1998-07-07', '2023-11-10',3),
       ('short-term deposit','ACTIV', 'RUB', 17.25, 100000.00,  '1998-07-07', '2023-10-27',1),
       ('short-term deposit','ACTIV', 'USD', 5.75, 10000,  '1998-07-07', '2023-10-27', 2),
       ('short-term deposit','ACTIV', 'EUR',  7.50,  120000, '1998-07-07', '2023-10-27', 3),
       ('average deposit', 'ACTIV', 'RUB', 17.25, 100000.00,  '1998-07-07', null, 1),
       ('average deposit', 'ACTIV', 'USD', 5.75, 10000,  '1998-07-07', null, 2),
       ('average deposit', 'ACTIV', 'EUR',  7.50,  120000,  '1998-07-07', null, 3),
       ('investment deposit', 'ACTIV', 'RUB', 17.25, 100000.00,  '1998-07-07', '2023-10-27', 1),
       ('investment deposit', 'ACTIV', 'USD', 5.75, 10000,  '1998-07-07', '2023-10-27' ,2),
       ('investment deposit', 'ACTIV', 'EUR',  7.50,  120000,  '1998-07-07', '2023-10-27', 3),
       ('short credit', 'ACTIV', 'RUB', 17.25, 100000.00,  '1998-07-07', null, 1),
       ('short credit', 'ACTIV', 'USD', 5.75, 10000,  '1998-07-07', null, 2),
       ('short credit', 'ACTIV', 'EUR',  7.50,  120000,  '1998-07-07', null, 3),
       ('average credit', 'ACTIV', 'RUB', 17.25, 100000.00,  '1998-07-07', '2023-10-27', 1),
       ('average credit', 'ACTIV', 'USD', 5.75, 10000,  '1998-07-07', '2023-10-27', 2),
       ('average credit', 'ACTIV', 'EUR',  7.50,  120000,  '1998-07-07', '2023-10-27', 3),
       ('long-term loan', 'ACTIV', 'RUB', 17.25, 100000.00, '1998-07-07', '2023-10-27', 1),
       ('long-term loan', 'ACTIV', 'USD', 5.75, 10000, '1998-07-07', '2023-10-27', 2),
       ('long-term loan', 'ACTIV', 'EUR',  7.50,  120000, '1998-07-07', '2023-10-27', 3),
       ('investment loan', 'ACTIV', 'RUB', 17.25, 100000.00,  '1998-07-07', '2023-10-27', 1),
       ('investment loan', 'ACTIV', 'USD', 5.75, 10000,  '1998-07-07', '2023-10-27', 2),
       ('investment loan', 'ACTIV', 'EUR',  7.50,  120000,  '1998-07-07', '2023-10-27', 3),
       ('secured loan', 'ACTIV', 'RUB', 17.25, 100000.00,  '1998-07-07', '2023-10-27', 1),
       ('secured loan', 'ACTIV', 'USD', 5.75, 10000, '1998-07-07', '2023-10-27', 2),
       ('secured loan', 'ACTIV', 'EUR',  7.50,  120000,  '1998-07-07', '2023-10-27', 3),
       ('real estate loan', 'ACTIV', 'RUB', 17.25, 100000.00,  '1998-07-07', '2023-10-27', 1),
       ('real estate loan', 'ACTIV', 'USD', 5.75, 10000,  '1998-07-07', '2023-10-27', 2),
       ('real estate loan', 'ACTIV', 'EUR',  7.50,  120000,  '1998-07-07', '2023-10-27', 3);


INSERT INTO public.account(
    name, type, status, balance, currency_code, created_at, updated_at, client_id)
VALUES ('number1', 'IRA', 'ACTIV', 2995645.56, 'RUB', '1998-07-07', null, 1),
       ('number2', 'IRA', 'ACTIV', 567834.57, 'RUB', '1998-07-07', null, 1),
       ('number3', 'IRA', 'ACTIV', 65463221365.58, 'RUB', '1998-07-07', null, 1),
       ('number4', 'IRA', 'ACTIV', 6543202525.59, 'RUB', '1998-07-07', null, 2),
       ('number5', 'IRA', 'ACTIV', 6542200.60, 'RUB', '1998-07-07', null, 3),
       ('number6', 'IRA', 'ACTIV', 56712124.61, 'RUB', '1998-07-07', null, 4),
       ('number7', 'IRA', 'ACTIV', 324512.62, 'RUB', '1998-07-07', null, 5),
       ('number8', 'IRA', 'ACTIV', 9688555.63, 'RUB', '1998-07-07', null, 6),
       ('number9', 'IRA', 'ACTIV', 8543.64, 'RUB', '1998-07-07', null, 7),
       ('number10', 'IRA', 'ACTIV', 100000.00, 'RUB', '1998-07-07', null, 8),
       ('number11', 'IRA', 'ACTIV', 100000.00, 'RUB', '1998-07-07', null, 9);



INSERT INTO public.transaction(type, amount, description,debit_account_id, credit_account_id,created_at)
VALUES (   'DEPOSIT', 100000, 'Перевод клиентом 9 со счета number11  клиенту 8 на счет number11', 9, 8, '2023-11-13');



INSERT INTO public.agreement( interest_rate, status, sum, created_at, updated_at, product_id, account_id)
VALUES (   17.25, 'ACTIV', 100000,  '1998-07-07', null,1, 1);





