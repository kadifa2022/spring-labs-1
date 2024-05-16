create table public.comment
(
    id     bigserial
        primary key,
    message    varchar(255),
    cinema_id  bigint,
    user_id    bigint
);