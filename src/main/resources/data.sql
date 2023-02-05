insert into "permission" ("description")
values ('ADMIN');

insert into "permission" ("description")
values ('USER');

insert into "user" ("cpf", "email", "enrollment", "is_admin", "blocked", "confirmed", "is_student", "is_ufcg_member", "name", "password", "phone_number", "valid_until")
values ('02134876085', 'davi@email.com', '119210111', FALSE, FALSE, FALSE, TRUE, TRUE, 'Davi Sousa', '$2a$10$az2.olI4i5xjHVS95FwfDO0bgq2.HSNsCEwt3q3WSaSK/LqfjrvDa', '83940028922', '2024-02-04 22:37:57.733');
/* a senha é 'minhasenha' */

insert into "user" ("cpf", "email", "enrollment", "is_admin", "blocked", "confirmed", "is_student", "is_ufcg_member", "name", "password", "phone_number", "valid_until")
values ('94756423078', 'kilian@email.com', '119210352', FALSE, FALSE, FALSE, TRUE, TRUE, 'Kilian', '$2a$10$az2.olI4i5xjHVS95FwfDO0bgq2.HSNsCEwt3q3WSaSK/LqfjrvDa', '83940028922', '2024-02-04 22:37:57.733');
/* a senha é 'minhasenha' */

insert into "user_permissions" ("user_id", "permissions_id")
values (1, 1);

insert into "user_permissions" ("user_id", "permissions_id")
values (2, 2);
