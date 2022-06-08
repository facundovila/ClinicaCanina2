
-- MASCOTAS
use db;
insert into mascota(peso, nombre, edad, sintomas, detalleTratamientos)values
                                                                         (15, 'hachi', 40, 'resfrio', 'te con limon'),
                                                                         (8,'firu', 8, 'gripe', 'optamox');







-- MEDICOS
insert into medico(disponibilidad, horarioEntrada, horarioSalida, nombre) VALUE (true, 10,15,'carlos');

insert into medico(disponibilidad, horarioEntrada, horarioSalida, nombre) VALUE (false, 10,15,'jose');

select * from medico;
