INSERT INTO public.client (status,tax_code,first_name, last_name, email, address
                          ,phone,  created_at, updated_at, manager_id)
VALUES
    ('ACTIV','C123456','Ivan', 'Fedorov','ivan_ged@list.ru', 'Gubkin 147', '+7 321 123-45-67', '1998-07-07', null, 1L)
    ;

INSERT INTO public.client (status, tax_code, first_name, last_name, email, address, phone, created_at, updated_at, manager_id) VALUES ('ACTIV', 'C123456', 'Ivan', 'Fedorov', 'Ivan_fed@list.ru', 'Gubkin 147', '+7(321) 123-45-67', '2023-11-13 20:59:44.000000', null, 2);