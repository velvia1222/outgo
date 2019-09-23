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
create table completed_message (
    created_at timestamp default current_timestamp,
    modified_at timestamp default current_timestamp,
    id serial primary key,
    message varchar(512)
);
insert into outgo (created_at, modified_at, status, buyer, amount, category) values (current_timestamp, current_timestamp, '0', 'n', 1000, '食費');
insert into outgo (created_at, modified_at, status, buyer, amount, category) values (current_timestamp, current_timestamp, '1', 'y', 2000, '娯楽');
insert into outgo (created_at, modified_at, status, buyer, amount, category) values (current_timestamp, current_timestamp, '0', 'n', 3000, '食費');
insert into outgo (created_at, modified_at, status, buyer, amount, category) values (current_timestamp, current_timestamp, '0', 'y', 4000, '娯楽');
insert into loginuser (created_at, modified_at, username, password) values (current_timestamp, current_timestamp, 'n', '$2a$10$1H9aEL5Ver/VpsAZOBa5cOiIVTxS34fzaxxVEyzbBRBuLCMEOGwdu');
insert into loginuser (created_at, modified_at, username, password) values (current_timestamp, current_timestamp, 'y', '$2a$10$WENrlZ1XOs/ODYAoj6bT6OMhsFlwjsJBKsOgDh0vUFSEVx2vSwWRi');
insert into completed_message (message) values ('May the force be with you.');
insert into completed_message (message) values ('Patience you must have, my young Padawan.');
insert into completed_message (message) values ('Power! Unlimited power!');
insert into completed_message (message) values ('The time has come. Execute Order 66.');
insert into completed_message (message) values ('It''s over Anakin! I have the high ground!');
insert into completed_message (message) values ('The Emperor is not as forgiving as I am.');
insert into completed_message (message) values ('I am your father.');
insert into completed_message (message) values ('This is my destiny!');
insert into completed_message (message) values ('He has a bad temper.');
insert into completed_message (message) values ('Accio!');
insert into completed_message (message) values ('Wingardium leviosa!');
insert into completed_message (message) values ('Expecto patronum!');
insert into completed_message (message) values ('Expelliarmus!');
insert into completed_message (message) values ('Obliviate!');
insert into completed_message (message) values ('Stupefy!');
insert into completed_message (message) values ('Piertotum locomotor!');
insert into completed_message (message) values ('Lumos maxima!');
insert into completed_message (message) values ('Reducto!');
insert into completed_message (message) values ('Imperio!');
insert into completed_message (message) values ('Crucio!');
insert into completed_message (message) values ('Avada Kedavra!');
insert into completed_message (message) values ('Sectumsempra!');
EOF