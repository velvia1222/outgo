create database outgo;

create table outgo.outgo (
    created_at timestamp default current_timestamp,
    modified_at timestamp default current_timestamp,
    id serial primary key,
    status char(1),
    buyer char(1),
    amount integer,
    category varchar(255)
);
create table outgo.loginuser (
    created_at timestamp default current_timestamp,
    modified_at timestamp default current_timestamp,
    id serial primary key,
    username varchar(64),
    password varchar(512)
);
create table outgo.completed_message (
    created_at timestamp default current_timestamp,
    modified_at timestamp default current_timestamp,
    id serial primary key,
    message varchar(512)
);
