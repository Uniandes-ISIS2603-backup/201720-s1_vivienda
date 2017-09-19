delete from AdministradorEntity;

insert into AdministradorEntity (documento, nombre, userName, password) values (1,'David','David123','cont123');
insert into AdministradorEntity (documento, nombre, userName, password) values (2,'Felipe','Felipe123','cont123');

delete from MensajeEntity;

insert into MensajeEntity (id, titulo, asunto, mensaje) values (1,'problema cafeteria','problema comida','no hay comida');
insert into MensajeEntity (id, titulo, asunto, mensaje) values (2,'problema cama','problema cama','no hay camas');