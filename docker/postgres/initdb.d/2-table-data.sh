#!/bin/bash
psql -U postgres -d outgo <<"EOF"
create table outgo (
    created_at timestamp default current_timestamp,
    modified_at timestamp default current_timestamp,
    id serial primary key,
    status char(1),
    buyer char(1),
    amount integer,
    category varchar(255)
);
create table loginuser (
    created_at timestamp default current_timestamp,
    modified_at timestamp default current_timestamp,
    id serial primary key,
    username varchar(64),
    password varchar(512)
);
insert into outgo (created_at, modified_at, status, buyer, amount, category) values (current_timestamp, current_timestamp, '0', 'n', 1000, '食費');
insert into outgo (created_at, modified_at, status, buyer, amount, category) values (current_timestamp, current_timestamp, '1', 'y', 2000, '娯楽');
insert into outgo (created_at, modified_at, status, buyer, amount, category) values (current_timestamp, current_timestamp, '0', 'n', 3000, '食費');
insert into outgo (created_at, modified_at, status, buyer, amount, category) values (current_timestamp, current_timestamp, '0', 'y', 4000, '娯楽');
insert into loginuser (created_at, modified_at, username, password) values (current_timestamp, current_timestamp, 'n', '$2a$10$1H9aEL5Ver/VpsAZOBa5cOiIVTxS34fzaxxVEyzbBRBuLCMEOGwdu');
insert into loginuser (created_at, modified_at, username, password) values (current_timestamp, current_timestamp, 'y', '$2a$10$WENrlZ1XOs/ODYAoj6bT6OMhsFlwjsJBKsOgDh0vUFSEVx2vSwWRi');
EOF
