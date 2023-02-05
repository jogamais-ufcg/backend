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

insert into "court_rules" ("appointment_duration", "appointment_period", "available_days", "closing_hour", "is_only_ufcg", "opening_hour", "recurrence_interval_period")
values (60, 'string', 'seg,ter,qua,qui,sex', 18, TRUE, 8, 10);

insert into "court" ("description", "name", "photo", "court_rules_id")
values ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut porta efficitur pulvinar. Ut eget ipsum eget odio viverra auctor auctor ut felis. Cras egestas dictum nibh, et porta purus fermentum vitae.', 'Quadra de Vôlei 1', 'photo-volei', 1);


insert into "court_rules" ("appointment_duration", "appointment_period", "available_days", "closing_hour", "is_only_ufcg", "opening_hour", "recurrence_interval_period")
values (90, 'string', 'seg,ter,qui,sex', 16, TRUE, 10, 10);

insert into "court" ("description", "name", "photo", "court_rules_id")
values ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut porta efficitur pulvinar. Ut eget ipsum eget odio viverra auctor auctor ut felis. Cras egestas dictum nibh, et porta purus fermentum vitae.', 'Quadra de Futsal 1', 'photo-futsal', 2);


insert into "court_rules" ("appointment_duration", "appointment_period", "available_days", "closing_hour", "is_only_ufcg", "opening_hour", "recurrence_interval_period")
values (60, 'string', 'seg,ter,qua,sex', 17, TRUE, 7, 10);

insert into "court" ("description", "name", "photo", "court_rules_id")
values ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut porta efficitur pulvinar. Ut eget ipsum eget odio viverra auctor auctor ut felis. Cras egestas dictum nibh, et porta purus fermentum vitae.', 'Quadra de Beach Tennis', 'photo-beach-tennis', 3);


insert into "court_rules" ("appointment_duration", "appointment_period", "available_days", "closing_hour", "is_only_ufcg", "opening_hour", "recurrence_interval_period")
values (120, 'string', 'seg,ter,qua,qui,sex', 15, FALSE, 9, 10);

insert into "court" ("description", "name", "photo", "court_rules_id")
values ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut porta efficitur pulvinar. Ut eget ipsum eget odio viverra auctor auctor ut felis. Cras egestas dictum nibh, et porta purus fermentum vitae.', 'Quadra de Tennis', 'photo-tennis', 4);
