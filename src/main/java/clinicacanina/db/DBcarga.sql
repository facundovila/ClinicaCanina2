
-- MASCOTAS

insert into mascota(edad, nombre, peso)values
                                                                         (15, 'hachi', 40),
                                                                         (8,'firu', 8);







-- MEDICOS
insert into medico(disponibilidad, horarioEntrada, horarioSalida, nombre) VALUE (true, 10,15,'carlos');

insert into medico(disponibilidad, horarioEntrada, horarioSalida, nombre) VALUE (false, 10,15,'jose');

select * from medico;
