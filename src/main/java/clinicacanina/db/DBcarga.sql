
-- MASCOTAS
use db;
insert into mascota(peso, nombre, edad, sintomas, detalleTratamientos)values
                                                                         (15, 'hachi', 40, 'resfrio', 'te con limon'),
                                                                         (8,'firu', 8, 'gripe', 'optamox');







-- MEDICOS
insert into medico(disponibilidad, horarioEntrada, horarioSalida, nombre, dni, contrasenia) VALUE (true, 10,15,'carlos',10, 'admin');

insert into medico(disponibilidad, horarioEntrada, horarioSalida, nombre, dni, contrasenia) VALUE (false, 10,15,'jose',11, 'admin2');






















-- QUERYS
select * from medico;

-- AMBULANCIA
INSERT INTO ambulancia
   (disponible, patente)
   VALUES(true, 'ABC123'),(true, 'DEF456'),(true, 'GHI789'),(true, 'JKL012');
