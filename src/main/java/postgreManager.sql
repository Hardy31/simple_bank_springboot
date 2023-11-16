
DROP TABLE IF EXISTS public.manager;


CREATE TABLE IF NOT EXISTS public.manager
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    first_name character varying(50) COLLATE pg_catalog."default",
    last_name character varying(50) COLLATE pg_catalog."default",
    status character varying(50) COLLATE pg_catalog."default",
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    CONSTRAINT manager_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.manager
    OWNER to bob;

COMMENT ON TABLE public.manager
    IS 'Table manager (Bank''s managers table)
';

INSERT INTO public.manager (first_name, last_name, status, created_at, updated_at)
VALUES
    ('Aron', 'Galpern', 'ACTIV', '1998-07-07', null),
    ('Haim', 'Mizrahim', 'ACTIV', '1998-07-07', '2023-11-10'),
    ('Samuil', 'Levi', 'ACTIV', '1998-07-07', '2023-10-27'),
    ('Tavia', 'Vaiser', 'PENSION', '1998-07-07', null),
    ('Zavul', 'Izrail', 'BUSINESS_TRIP', '1998-07-07', '2023-10-27'),
    ('Abransam', 'Messolam', 'BUSINESS_TRIP', '1998-07-07', null),
    ('Larik', 'Valser', 'NOT_ACTIVE', '1998-07-07', '2023-10-27'),
    ('Lisak', 'dahan', 'ACTIV', '1998-07-07', '2023-10-27'),
    ('Samuil', 'Izrail', 'PENSION', '1998-07-07', null);

delete from public.manager ;



