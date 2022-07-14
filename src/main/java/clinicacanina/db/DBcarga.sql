
-- MASCOTAS
use db;


insert into mascota(peso, nombre, edad)values
                                                                         (15, 'hachi', 40 ),
                                                                         (8,'firu', 8);


insert into visitaclinica(sintomas,tratamiento, fecha) values ('sintomas', 'tratamientoje', '2021-10-07');
insert into visitaclinica(sintomas,tratamiento, fecha) values ('sintomas', 'tratamiento22','2021-10-10');

update visitaclinica set `mascota_id` = 1 where id =1;
update visitaclinica set `mascota_id` = 2 where id =2;
update visitaclinica set `fecha` = CURDATE() where id =2;




-- MEDICOS
insert into medico(disponibilidad, horarioEntrada, horarioSalida, nombre, dni, contrasenia) VALUE (true, 10,15,'carlos',10, 'admin');

insert into medico(disponibilidad, horarioEntrada, horarioSalida, nombre, dni, contrasenia) VALUE (false, 10,15,'jose',11, 'admin2');

insert into medico( disponibilidad, horarioEntrada, horarioSalida, nombre, dni, contrasenia) VALUE (true, 10,16,'carlos',1 , 'uno');

insert into medico( disponibilidad, horarioEntrada, horarioSalida, nombre, dni, contrasenia) VALUE ( true, 11,12,'lucas',2 , 'dos');

insert into medico( disponibilidad, horarioEntrada, horarioSalida, nombre, dni, contrasenia) VALUE ( true, 13,17,'camila', 3, 'tres');


-- QUERYS
select * from medico;

select * from usuario;

select * from mascota;

-- AMBULANCIA
INSERT INTO ambulancia
   (disponible, patente)
   VALUES(true, 'ABC123'),(true, 'DEF456'),(true, 'GHI789'),(true, 'JKL012');

select * from usuario;

select * from visitaclinica;