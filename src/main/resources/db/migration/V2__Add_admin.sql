insert into usr (id, username, password, active)
    values (400, 'admin', '123', true);

insert into user_role (user_id, roles)
    values (400, 'USER'), (400, 'ADMIN');

