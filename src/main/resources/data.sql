insert into "permission" ("description")
values ('ADMIN');

insert into "permission" ("description")
values ('USER');

/* davi@email.com é admin */
insert into "user" ("cpf", "email", "enrollment", "is_admin", "blocked", "confirmed", "is_student", "is_ufcg_member", "name", "password", "phone_number", "valid_until")
values ('02134876085', 'davi@email.com', NULL, FALSE, FALSE, FALSE, TRUE, FALSE, 'Davi Sousa', '$2a$10$az2.olI4i5xjHVS95FwfDO0bgq2.HSNsCEwt3q3WSaSK/LqfjrvDa', '83940028922', '2030-02-04 22:37:57.733');
/* a senha é 'minhasenha' */

/* kilian@email.com é estudante */
insert into "user" ("cpf", "email", "enrollment", "is_admin", "blocked", "confirmed", "is_student", "is_ufcg_member", "name", "password", "phone_number", "valid_until")
values ('94756423078', 'kilian@email.com', '119210352', FALSE, FALSE, FALSE, TRUE, TRUE, 'Kilian', '$2a$10$az2.olI4i5xjHVS95FwfDO0bgq2.HSNsCEwt3q3WSaSK/LqfjrvDa', '83940028922', '2030-02-04 22:37:57.733');
/* a senha é 'minhasenha' */

/* mariane@email.com é servidora */
insert into "user" ("cpf", "email", "enrollment", "is_admin", "blocked", "confirmed", "is_student", "is_ufcg_member", "name", "password", "phone_number", "valid_until")
values ('71995449016', 'mariane@email.com', NULL, FALSE, FALSE, TRUE, FALSE, TRUE, 'Mariane', '$2a$10$az2.olI4i5xjHVS95FwfDO0bgq2.HSNsCEwt3q3WSaSK/LqfjrvDa', '83940028922', '2030-02-04 22:37:57.733');
/* a senha é 'minhasenha' */

/* eduardo@email.com é user externo */
insert into "user" ("cpf", "email", "enrollment", "is_admin", "blocked", "confirmed", "is_student", "is_ufcg_member", "name", "password", "phone_number", "valid_until")
values ('70326612033', 'eduardo@email.com', NULL, FALSE, FALSE, TRUE, FALSE, FALSE, 'Eduardo', '$2a$10$az2.olI4i5xjHVS95FwfDO0bgq2.HSNsCEwt3q3WSaSK/LqfjrvDa', '83940028922', '2030-02-04 22:37:57.733');
/* a senha é 'minhasenha' */

insert into "user_permissions" ("user_id", "permissions_id")
values (1, 1);

insert into "user_permissions" ("user_id", "permissions_id")
values (2, 2);

insert into "user_permissions" ("user_id", "permissions_id")
values (3, 2);

insert into "user_permissions" ("user_id", "permissions_id")
values (4, 2);
