create database outgo;
use outgo;
create table outgo (
    number int auto_increment primary key,
    status char(1),
    buyer char(1),
    amount int,
    category varchar(255)
);
insert into outgo (status, buyer, amount, category) values ('0', 'n', 1000, '食費');
insert into outgo (status, buyer, amount, category) values ('1', 'y', 2000, '娯楽');
