

INSERT INTO `db`.`usuario` (`id`, `email`, `password`) VALUES ('1', 'usuario@usuario.com', '1');
INSERT INTO `db`.`mascota` (`edad`, `nombre`, `peso`, `usuario_id`) VALUES (1, 'toto', 3, 1),(1, "mostaza", 3,1);


-- MEDICOS
insert into medico(disponibilidad, horarioEntrada, horarioSalida, nombre, dni, contrasenia) VALUE (true, 10,15,'carlos',10, 'admin');

insert into medico(disponibilidad, horarioEntrada, horarioSalida, nombre, dni, contrasenia) VALUE (false, 10,15,'jose',11, 'admin2');

insert into medico( disponibilidad, horarioEntrada, horarioSalida, nombre, dni, contrasenia) VALUE (true, 10,16,'carlos',1 , 'uno');


-- QUERYS
select * from medico;

-- AMBULANCIAusuario

insert into medico( disponibilidad, horarioEntrada, horarioSalida, nombre, dni, contrasenia) VALUE ( true, 11,12,'lucas',2 , 'dos');

insert into medico( disponibilidad, horarioEntrada, horarioSalida, nombre, dni, contrasenia) VALUE ( true, 13,17,'camila', 3, 'tres');


-- QUERYS
select * from medico;

select * from usuario;

select * from mascota;

-- AMBULANCIA

INSERT INTO ambulancia(disponible, patente)
VALUES(true, 'ABC123'),(true, 'DEF456'),(true, 'GHI789'),(true, 'JKL012');

INSERT INTO `db`.`turno` (`estado`, `fechaTurno`, `medico_id`) VALUES (1, '2022-06-06 20:00:00.000000', '1'),(1, '2022-06-29 10:00:00', '1')
                                                                    ,(1, '2022-06-29 11:00:00', '1')
                                                                    ,(1, '2022-06-29 12:00:00', '1')
                                                                    ,(1, '2022-06-29 13:00:00', '1')
                                                                    ,(1, '2022-06-29 14:00:00', '1')
                                                                    ,(1, '2022-06-29 15:00:00', '1')
                                                                    ,(1, '2022-06-29 16:00:00', '1')
                                                                    ,(1, '2022-06-29 17:00:00', '1')
                                                                    ,(1, '2022-06-29 18:00:00', '1')
                                                                    ,(1, '2022-06-30 10:00:00', '1')
                                                                    ,(1, '2022-06-30 11:00:00', '1')
                                                                    ,(1, '2022-06-30 12:00:00', '1')
                                                                    ,(1, '2022-06-30 13:00:00', '1')
                                                                    ,(1, '2022-06-30 14:00:00', '1')
                                                                    ,(1, '2022-06-30 15:00:00', '1')
                                                                    ,(1, '2022-06-30 16:00:00', '1')
                                                                    ,(1, '2022-06-30 17:00:00', '1')
                                                                    ,(1, '2022-06-30 18:00:00', '1')
                                                                    ,(1, '2022-07-1 10:00:00', '1')
                                                                    ,(1, '2022-07-1 11:00:00', '1')
                                                                    ,(1, '2022-07-1 12:00:00', '1')
                                                                    ,(1, '2022-07-1 13:00:00', '1')
                                                                    ,(1, '2022-07-1 14:00:00', '1')
                                                                    ,(1, '2022-07-1 15:00:00', '1')
                                                                    ,(1, '2022-07-1 16:00:00', '1')
                                                                    ,(1, '2022-07-1 17:00:00', '1')
                                                                    ,(1, '2022-07-1 18:00:00', '1')
                                                                    ,(1, '2022-07-5 10:00:00', '1')
                                                                    ,(1, '2022-07-5 11:00:00', '1')
                                                                    ,(1, '2022-07-5 12:00:00', '1')
                                                                    ,(1, '2022-07-5 13:00:00', '1')
                                                                    ,(1, '2022-07-5 14:00:00', '1')
                                                                    ,(1, '2022-07-5 15:00:00', '1')
                                                                    ,(1, '2022-07-5 16:00:00', '1')
                                                                    ,(1, '2022-07-5 17:00:00', '1')
                                                                    ,(1, '2022-07-5 18:00:00', '1')
                                                                    ,(1, '2022-07-6 10:00:00', '1')
                                                                    ,(1, '2022-07-6 11:00:00', '1')
                                                                    ,(1, '2022-07-6 12:00:00', '1')
                                                                    ,(1, '2022-07-6 13:00:00', '1')
                                                                    ,(1, '2022-07-6 14:00:00', '1')
                                                                    ,(1, '2022-07-6 15:00:00', '1')
                                                                    ,(1, '2022-07-6 16:00:00', '1')
                                                                    ,(1, '2022-07-6 17:00:00', '1')
                                                                    ,(1, '2022-07-6 18:00:00', '1')
                                                                    ,(1, '2022-07-7 10:00:00', '1')
                                                                    ,(1, '2022-07-7 11:00:00', '1')
                                                                    ,(1, '2022-07-7 12:00:00', '1')
                                                                    ,(1, '2022-07-7 13:00:00', '1')
                                                                    ,(1, '2022-07-7 14:00:00', '1')
                                                                    ,(1, '2022-07-7 15:00:00', '1')
                                                                    ,(1, '2022-07-7 16:00:00', '1')
                                                                    ,(1, '2022-07-7 17:00:00', '1')
                                                                    ,(1, '2022-07-7 18:00:00', '1')
                                                                    ,(1, '2022-07-8 10:00:00', '1')
                                                                    ,(1, '2022-07-8 11:00:00', '1')
                                                                    ,(1, '2022-07-8 12:00:00', '1')
                                                                    ,(1, '2022-07-8 13:00:00', '1')
                                                                    ,(1, '2022-07-8 14:00:00', '1')
                                                                    ,(1, '2022-07-8 15:00:00', '1')
                                                                    ,(1, '2022-07-8 16:00:00', '1')
                                                                    ,(1, '2022-07-8 17:00:00', '1')
                                                                    ,(1, '2022-07-9 18:00:00', '1')
                                                                    ,(1, '2022-07-9 10:00:00', '1')
                                                                    ,(1, '2022-07-9 11:00:00', '1')
                                                                    ,(1, '2022-07-9 12:00:00', '1')
                                                                    ,(1, '2022-07-9 13:00:00', '1')
                                                                    ,(1, '2022-07-9 14:00:00', '1')
                                                                    ,(1, '2022-07-9 15:00:00', '1')
                                                                    ,(1, '2022-07-9 16:00:00', '1')
                                                                    ,(1, '2022-07-9 17:00:00', '1')
                                                                    ,(1, '2022-07-9 18:00:00', '1')
                                                                    ,(1, '2022-07-10 10:00:00', '1')
                                                                    ,(1, '2022-07-10 11:00:00', '1')
                                                                    ,(1, '2022-07-10 12:00:00', '1')
                                                                    ,(1, '2022-07-10 13:00:00', '1')
                                                                    ,(1, '2022-07-10 14:00:00', '1')
                                                                    ,(1, '2022-07-10 15:00:00', '1')
                                                                    ,(1, '2022-07-10 16:00:00', '1')
                                                                    ,(1, '2022-07-10 17:00:00', '1')
                                                                    ,(1, '2022-07-10 18:00:00', '1')
                                                                    ,(1, '2022-07-11 10:00:00', '1')
                                                                    ,(1, '2022-07-11 11:00:00', '1')
                                                                    ,(1, '2022-07-11 12:00:00', '1')
                                                                    ,(1, '2022-07-11 13:00:00', '1')
                                                                    ,(1, '2022-07-11 14:00:00', '1')
                                                                    ,(1, '2022-07-11 15:00:00', '1')
                                                                    ,(1, '2022-07-11 16:00:00', '1')
                                                                    ,(1, '2022-07-11 17:00:00', '1')
                                                                    ,(1, '2022-07-11 18:00:00', '1')
                                                                    ,(1, '2022-07-12 10:00:00', '1')
                                                                    ,(1, '2022-07-12 11:00:00', '1')
                                                                    ,(1, '2022-07-12 12:00:00', '1')
                                                                    ,(1, '2022-07-12 13:00:00', '1')
                                                                    ,(1, '2022-07-12 14:00:00', '1')
                                                                    ,(1, '2022-07-12 15:00:00', '1')
                                                                    ,(1, '2022-07-12 16:00:00', '1')
                                                                    ,(1, '2022-07-12 17:00:00', '1')
                                                                    ,(1, '2022-07-12 18:00:00', '1')
                                                                    ,(1,'2022-7-13 11:00:00','1')
                                                                    ,(1,'2022-7-13 12:00:00','1')
                                                                    ,(1,'2022-7-13 13:00:00','1')
                                                                    ,(1,'2022-7-13 14:00:00','1')
                                                                    ,(1,'2022-7-13 15:00:00','1')
                                                                    ,(1,'2022-7-13 16:00:00','1')
                                                                    ,(1,'2022-7-13 17:00:00','1')
                                                                    ,(1,'2022-7-13 18:00:00','1')
                                                                    ,(1,'2022-7-13 19:00:00','1')
                                                                    ,(1,'2022-7-13 20:00:00','1')
                                                                    ,(1,'2022-7-13 21:00:00','1')
                                                                    ,(1,'2022-7-14 11:00:00','1')
                                                                    ,(1,'2022-7-14 12:00:00','1')
                                                                    ,(1,'2022-7-14 13:00:00','1')
                                                                    ,(1,'2022-7-14 14:00:00','1')
                                                                    ,(1,'2022-7-14 15:00:00','1')
                                                                    ,(1,'2022-7-14 16:00:00','1')
                                                                    ,(1,'2022-7-14 17:00:00','1')
                                                                    ,(1,'2022-7-14 18:00:00','1')
                                                                    ,(1,'2022-7-14 19:00:00','1')
                                                                    ,(1,'2022-7-14 20:00:00','1')
                                                                    ,(1,'2022-7-14 21:00:00','1')
                                                                    ,(1,'2022-7-15 11:00:00','1')
                                                                    ,(1,'2022-7-15 12:00:00','1')
                                                                    ,(1,'2022-7-15 13:00:00','1')
                                                                    ,(1,'2022-7-15 14:00:00','1')
                                                                    ,(1,'2022-7-15 15:00:00','1')
                                                                    ,(1,'2022-7-15 16:00:00','1')
                                                                    ,(1,'2022-7-15 17:00:00','1')
                                                                    ,(1,'2022-7-15 18:00:00','1')
                                                                    ,(1,'2022-7-15 19:00:00','1')
                                                                    ,(1,'2022-7-15 20:00:00','1')
                                                                    ,(1,'2022-7-15 21:00:00','1')
                                                                    ,(1,'2022-7-16 11:00:00','1')
                                                                    ,(1,'2022-7-16 12:00:00','1')
                                                                    ,(1,'2022-7-16 13:00:00','1')
                                                                    ,(1,'2022-7-16 14:00:00','1')
                                                                    ,(1,'2022-7-16 15:00:00','1')
                                                                    ,(1,'2022-7-16 16:00:00','1')
                                                                    ,(1,'2022-7-16 17:00:00','1')
                                                                    ,(1,'2022-7-16 18:00:00','1')
                                                                    ,(1,'2022-7-16 19:00:00','1')
                                                                    ,(1,'2022-7-16 20:00:00','1')
                                                                    ,(1,'2022-7-16 21:00:00','1')
                                                                    ,(1,'2022-7-17 11:00:00','1')
                                                                    ,(1,'2022-7-17 12:00:00','1')
                                                                    ,(1,'2022-7-17 13:00:00','1')
                                                                    ,(1,'2022-7-17 14:00:00','1')
                                                                    ,(1,'2022-7-17 15:00:00','1')
                                                                    ,(1,'2022-7-17 16:00:00','1')
                                                                    ,(1,'2022-7-17 17:00:00','1')
                                                                    ,(1,'2022-7-17 18:00:00','1')
                                                                    ,(1,'2022-7-17 19:00:00','1')
                                                                    ,(1,'2022-7-17 20:00:00','1')
                                                                    ,(1,'2022-7-17 21:00:00','1')
                                                                    ,(1,'2022-7-18 11:00:00','1')
                                                                    ,(1,'2022-7-18 12:00:00','1')
                                                                    ,(1,'2022-7-18 13:00:00','1')
                                                                    ,(1,'2022-7-18 14:00:00','1')
                                                                    ,(1,'2022-7-18 15:00:00','1')
                                                                    ,(1,'2022-7-18 16:00:00','1')
                                                                    ,(1,'2022-7-18 17:00:00','1')
                                                                    ,(1,'2022-7-18 18:00:00','1')
                                                                    ,(1,'2022-7-18 19:00:00','1')
                                                                    ,(1,'2022-7-18 20:00:00','1')
                                                                    ,(1,'2022-7-18 21:00:00','1')
                                                                    ,(1,'2022-7-19 11:00:00','1')
                                                                    ,(1,'2022-7-19 12:00:00','1')
                                                                    ,(1,'2022-7-19 13:00:00','1')
                                                                    ,(1,'2022-7-19 14:00:00','1')
                                                                    ,(1,'2022-7-19 15:00:00','1')
                                                                    ,(1,'2022-7-19 16:00:00','1')
                                                                    ,(1,'2022-7-19 17:00:00','1')
                                                                    ,(1,'2022-7-19 18:00:00','1')
                                                                    ,(1,'2022-7-19 19:00:00','1')
                                                                    ,(1,'2022-7-19 20:00:00','1')
                                                                    ,(1,'2022-7-19 21:00:00','1')
                                                                    ,(1,'2022-7-20 11:00:00','1')
                                                                    ,(1,'2022-7-20 12:00:00','1')
                                                                    ,(1,'2022-7-20 13:00:00','1')
                                                                    ,(1,'2022-7-20 14:00:00','1')
                                                                    ,(1,'2022-7-20 15:00:00','1')
                                                                    ,(1,'2022-7-20 16:00:00','1')
                                                                    ,(1,'2022-7-20 17:00:00','1')
                                                                    ,(1,'2022-7-20 18:00:00','1')
                                                                    ,(1,'2022-7-20 19:00:00','1')
                                                                    ,(1,'2022-7-20 20:00:00','1')
                                                                    ,(1,'2022-7-20 21:00:00','1')
                                                                    ,(1,'2022-7-21 11:00:00','1')
                                                                    ,(1,'2022-7-21 12:00:00','1')
                                                                    ,(1,'2022-7-21 13:00:00','1')
                                                                    ,(1,'2022-7-21 14:00:00','1')
                                                                    ,(1,'2022-7-21 15:00:00','1')
                                                                    ,(1,'2022-7-21 16:00:00','1')
                                                                    ,(1,'2022-7-21 17:00:00','1')
                                                                    ,(1,'2022-7-21 18:00:00','1')
                                                                    ,(1,'2022-7-21 19:00:00','1')
                                                                    ,(1,'2022-7-21 20:00:00','1')
                                                                    ,(1,'2022-7-21 21:00:00','1')
                                                                    ,(1,'2022-7-22 11:00:00','1')
                                                                    ,(1,'2022-7-22 12:00:00','1')
                                                                    ,(1,'2022-7-22 13:00:00','1')
                                                                    ,(1,'2022-7-22 14:00:00','1')
                                                                    ,(1,'2022-7-22 15:00:00','1')
                                                                    ,(1,'2022-7-22 16:00:00','1')
                                                                    ,(1,'2022-7-22 17:00:00','1')
                                                                    ,(1,'2022-7-22 18:00:00','1')
                                                                    ,(1,'2022-7-22 19:00:00','1')
                                                                    ,(1,'2022-7-22 20:00:00','1')
                                                                    ,(1,'2022-7-22 21:00:00','1')
                                                                    ,(1,'2022-7-23 11:00:00','1')
                                                                    ,(1,'2022-7-23 12:00:00','1')
                                                                    ,(1,'2022-7-23 13:00:00','1')
                                                                    ,(1,'2022-7-23 14:00:00','1')
                                                                    ,(1,'2022-7-23 15:00:00','1')
                                                                    ,(1,'2022-7-23 16:00:00','1')
                                                                    ,(1,'2022-7-23 17:00:00','1')
                                                                    ,(1,'2022-7-23 18:00:00','1')
                                                                    ,(1,'2022-7-23 19:00:00','1')
                                                                    ,(1,'2022-7-23 20:00:00','1')
                                                                    ,(1,'2022-7-23 23:59:00','1')
                                                                    ,(1,'2022-7-24 11:00:00','1')
                                                                    ,(1,'2022-7-24 12:00:00','1')
                                                                    ,(1,'2022-7-24 13:00:00','1')
                                                                    ,(1,'2022-7-24 14:00:00','1')
                                                                    ,(1,'2022-7-24 15:00:00','1')
                                                                    ,(1,'2022-7-24 16:00:00','1')
                                                                    ,(1,'2022-7-24 17:00:00','1')
                                                                    ,(1,'2022-7-24 18:00:00','1')
                                                                    ,(1,'2022-7-24 19:00:00','1')
                                                                    ,(1,'2022-7-24 20:00:00','1')
                                                                    ,(1,'2022-7-24 21:00:00','1')
                                                                    ,(1,'2022-7-26 11:00:00','1')
                                                                    ,(1,'2022-7-26 12:00:00','1')
                                                                    ,(1,'2022-7-26 13:00:00','1')
                                                                    ,(1,'2022-7-26 14:00:00','1')
                                                                    ,(1,'2022-7-26 15:00:00','1')
                                                                    ,(1,'2022-7-26 16:00:00','1')
                                                                    ,(1,'2022-7-26 17:00:00','1')
                                                                    ,(1,'2022-7-26 18:00:00','1')
                                                                    ,(1,'2022-7-26 19:00:00','1')
                                                                    ,(1,'2022-7-26 20:00:00','1')
                                                                    ,(1,'2022-7-26 21:00:00','1')
                                                                    ,(1,'2022-7-27 11:00:00','1')
                                                                    ,(1,'2022-7-27 12:00:00','1')
                                                                    ,(1,'2022-7-27 13:00:00','1')
                                                                    ,(1,'2022-7-27 14:00:00','1')
                                                                    ,(1,'2022-7-27 15:00:00','1')
                                                                    ,(1,'2022-7-27 16:00:00','1')
                                                                    ,(1,'2022-7-27 17:00:00','1')
                                                                    ,(1,'2022-7-27 18:00:00','1')
                                                                    ,(1,'2022-7-27 19:00:00','1')
                                                                    ,(1,'2022-7-27 20:00:00','1')
                                                                    ,(1,'2022-7-27 21:00:00','1')
                                                                    ,(1,'2022-7-28 11:00:00','1')
                                                                    ,(1,'2022-7-28 12:00:00','1')
                                                                    ,(1,'2022-7-28 13:00:00','1')
                                                                    ,(1,'2022-7-28 14:00:00','1')
                                                                    ,(1,'2022-7-28 15:00:00','1')
                                                                    ,(1,'2022-7-28 16:00:00','1')
                                                                    ,(1,'2022-7-28 17:00:00','1')
                                                                    ,(1,'2022-7-28 18:00:00','1')
                                                                    ,(1,'2022-7-28 19:00:00','1')
                                                                    ,(1,'2022-7-28 20:00:00','1')
                                                                    ,(1,'2022-7-28 21:00:00','1')
                                                                    ,(1,'2022-7-29 11:00:00','1')
                                                                    ,(1,'2022-7-29 12:00:00','1')
                                                                    ,(1,'2022-7-29 13:00:00','1')
                                                                    ,(1,'2022-7-29 14:00:00','1')
                                                                    ,(1,'2022-7-29 15:00:00','1')
                                                                    ,(1,'2022-7-29 16:00:00','1')
                                                                    ,(1,'2022-7-29 17:00:00','1')
                                                                    ,(1,'2022-7-29 18:00:00','1')
                                                                    ,(1,'2022-7-29 19:00:00','1')
                                                                    ,(1,'2022-7-29 20:00:00','1')
                                                                    ,(1,'2022-7-29 21:00:00','1')
                                                                    ,(1,'2022-7-30 11:00:00','1')
                                                                    ,(1,'2022-7-30 12:00:00','1')
                                                                    ,(1,'2022-7-30 13:00:00','1')
                                                                    ,(1,'2022-7-30 14:00:00','1')
                                                                    ,(1,'2022-7-30 15:00:00','1')
                                                                    ,(1,'2022-7-30 16:00:00','1')
                                                                    ,(1,'2022-7-30 17:00:00','1')
                                                                    ,(1,'2022-7-30 18:00:00','1')
                                                                    ,(1,'2022-7-30 19:00:00','1')
                                                                    ,(1,'2022-7-30 20:00:00','1')
                                                                    ,(1,'2022-7-30 21:00:00','1');

UPDATE `db`.`turno` SET `mascota_id` = '1', `usuario_id` = '1' WHERE (`id` = '1');
UPDATE `db`.`turno` SET `mascota_id` = '1', `usuario_id` = '1' WHERE (`id` = '4');
UPDATE `db`.`turno` SET `mascota_id` = '1', `usuario_id` = '1' WHERE (`id` = '5');
UPDATE `db`.`turno` SET `mascota_id` = '1', `usuario_id` = '1' WHERE (`id` = '6');
UPDATE `db`.`turno` SET `mascota_id` = '1', `usuario_id` = '1' WHERE (`id` = '31');
UPDATE `db`.`turno` SET estado = false, mascota_id = 1, usuario_id = 1 where id = 135;
UPDATE `db`.`turno` SET estado = false, mascota_id = 1, usuario_id = 1 where id = 160;



