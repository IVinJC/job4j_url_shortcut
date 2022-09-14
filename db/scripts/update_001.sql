create table IF NOT EXISTS site
(
    id       serial primary key,
    site     varchar(255) unique,
    login    varchar(255) NOT NULL unique,
    password varchar(255) NOT NULL
);

create table IF NOT EXISTS urls
(
    id     serial primary key,
    url    varchar(500) NOT NULL unique,
    code   varchar(255) NOT NULL unique,
    url_id int
);