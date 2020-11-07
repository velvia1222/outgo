insert into outgo.outgo (created_at, modified_at, status, buyer, amount, category) values
    (current_timestamp, current_timestamp, '0', 'n', 1000, '食費'),
    (current_timestamp, current_timestamp, '1', 'y', 2000, '娯楽'),
    (current_timestamp, current_timestamp, '0', 'n', 3000, '食費'),
    (current_timestamp, current_timestamp, '0', 'y', 4000, '娯楽');
insert into outgo.loginuser (created_at, modified_at, username, password) values
    (current_timestamp, current_timestamp, 'n', '$2a$10$1H9aEL5Ver/VpsAZOBa5cOiIVTxS34fzaxxVEyzbBRBuLCMEOGwdu'),
    (current_timestamp, current_timestamp, 'y', '$2a$10$WENrlZ1XOs/ODYAoj6bT6OMhsFlwjsJBKsOgDh0vUFSEVx2vSwWRi');
insert into outgo.completed_message (message) values
    ('May the force be with you.'),
    ('Patience you must have, my young Padawan.'),
    ('Power! Unlimited power!'),
    ('The time has come. Execute Order 66.'),
    ('It''s over Anakin! I have the high ground!'),
    ('The Emperor is not as forgiving as I am.'),
    ('I am your father.'),
    ('This is my destiny!'),
    ('He has a bad temper.'),
    ('Accio!'),
    ('Wingardium leviosa!'),
    ('Expecto patronum!'),
    ('Expelliarmus!'),
    ('Obliviate!'),
    ('Stupefy!'),
    ('Piertotum locomotor!'),
    ('Lumos maxima!'),
    ('Reducto!'),
    ('Imperio!'),
    ('Crucio!'),
    ('Avada Kedavra!'),
    ('Sectumsempra!');
