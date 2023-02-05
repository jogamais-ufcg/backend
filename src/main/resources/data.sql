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
values ('Medindo 18 metros de comprimento por 9 metros de largura, esta quadra é ideal para jogadores de todos os níveis e é perfeita para competições, treinos ou jogos informais com amigos.', 'Quadra de Vôlei', 'photo-volei', 1);


insert into "court_rules" ("appointment_duration", "appointment_period", "available_days", "closing_hour", "is_only_ufcg", "opening_hour", "recurrence_interval_period")
values (90, 'string', 'seg,ter,qui,sex', 16, TRUE, 10, 10);

insert into "court" ("description", "name", "photo", "court_rules_id")
values ('Nossa quadra oferece medidas de 38x18m, piso antiderrapante e confortável, com iluminação adequada para jogos diurnos e noturnos.', 'Quadra de Futsal', 'photo-futsal', 2);


insert into "court_rules" ("appointment_duration", "appointment_period", "available_days", "closing_hour", "is_only_ufcg", "opening_hour", "recurrence_interval_period")
values (60, 'string', 'seg,ter,qua,sex', 17, TRUE, 7, 10);

insert into "court" ("description", "name", "photo", "court_rules_id")
values ('Nossa quadra de Beach Tennis é ideal para jogadores de todos os níveis e possui medidas aproximadas de 10 metros x 20 metros. O solo é coberto com areia fina para garantir a estabilidade dos jogadores durante as partidas.', 'Quadra de Beach Tennis', 'photo-beach-tennis', 3);


insert into "court_rules" ("appointment_duration", "appointment_period", "available_days", "closing_hour", "is_only_ufcg", "opening_hour", "recurrence_interval_period")
values (120, 'string', 'seg,ter,qua,qui,sex', 15, FALSE, 9, 10);

insert into "court" ("description", "name", "photo", "court_rules_id")
values ('Nossa quadra de tênis possui medidas padrão de 23,77 metros por 8,23 metros, e o solo é coberto com superfície uniforme para garantir a estabilidade dos jogadores. A iluminação adequada permite jogar durante o dia ou à noite.', 'Quadra de Tênis', 'photo-tennis', 4);
