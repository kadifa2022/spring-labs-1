create table public.account_details
(
    id        bigserial
        primary key,
    address     varchar(255),
    age         integer,
    city        varchar(255),
    country     varchar(255),
    name        varchar(255),
    postal_code varchar(255),
    role        varchar(255),
    state       varchar(255)
);

create table public.genre
(
    id        bigserial
        primary key,
    name     varchar(255)
);

create table public.location
(
    id        bigserial
        primary key,
    address       varchar(255).
    city          varchar(255),
    country       varchar(255),
    latitude      numeric(19, 2),
    longitude     numeric(19, 2),
    name          varchar(255),
    postal_code   varchar(255),
    state         varchar(255)
);

create table public.cinema
(
    id        bigserial
        primary key,
    name           varchar(255),
    sponsored_name varchar (255),
    location_id    bigint
         constraint fk8s0buef0qbajg608344o3mmvl
            references public.location
);

create table public.movie
(
    id        bigserial
        primary key,
    duration      integer,
    name          varchar(255),
    price         numeric(19, 2),
    release_date  date,
    state         varchar(255),
    summary       text,
    type          varchar(255)
);

create table public.movie_genre_rel
(
    movie_id bigint not null
        constraint fk7nhifftg0au66clnpt18bc9ut
            references public..movie,
    genre_id bigint not null
        constraint fkj6inrcrjt89pje7q4wkc8cvm1
            references public.genre
);

create table public.movie_cinema
(
    id        bigserial
        primary key,
    date_time timestamp,
    cinema_id bigint
        constraint fk3bn2ho0qwu0c7tgfmxj8fuygn
            references public.cinema,
    movie_id bigint
        constraint fk2trmgwjpxxd4q7kcupa6m1vu
            references public..movie
);

create table public.user_account
(
    id        bigserial
        primary key,
    email           varchar(255),
    password        varchar(255),
    username        varchar(255),
    account_details_id bigint
        constraint fksukm6xaj1w3rd96a82j1sy6dq
            references public.account_details
);

create table public.ticket
(
    id        bigserial
        primary key,
    date_time     timestamp,
    row_number    integer,
    seat_number   integer,
    movie_cinema_id bigint
        constraint fknsvmk94garu0s4ojnbpn6annb
            references public.movie_cinema,
    user_account_id bigint
        constraint fk7tjg2ere9k3sy94bt7c8a2jbt
            references public.user_account
);
