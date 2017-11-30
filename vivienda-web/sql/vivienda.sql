delete from ApartamentoEntity;
delete from PisoEntity;
delete from TorreEntity;
delete from TarjetaEntity;
delete from OrdenPagoEntity;
delete from CuentaEntity;
delete from MensajeEntity;
delete from SugerenciaEntity;
delete from EstudianteEntity;
delete from ADMINISTRADORENTITY_SUGERENCIAENTITY;
delete from AdministradorEntity;
delete from ServicioEntity;
delete from PrestadorEntity;

insert into PrestadorEntity (nombre, documento, disponible) values ('DesarrolloServicios', 101010, 1);
insert into PrestadorEntity (nombre, documento, disponible) values ('CesarServices', 303030, 1);
insert into ServicioEntity (nombre, Precio, myprestador_documento) values('Limpieza', 1000,303030);
insert into ServicioEntity (nombre, precio, myprestador_documento) values('alimentacion', 2000,303030);
insert into AdministradorEntity (documento, nombre, username, password) values (100,'David', 'DavidCuenta,', '123hue');
insert into AdministradorEntity (documento, nombre, username, password) values (200,'David', 'DavidCuenta,', '123hue');

insert into MensajeEntity (id, titulo, asunto, mensaje) values (100,'ErrorRaro', 'Hubo un error raro,', 'Error raro');
insert into MensajeEntity (id, titulo, asunto, mensaje) values (200,'Muerto', 'Hubo un muerto,', 'Alguien murio');

insert into TarjetaEntity (nombre, numeroTarjeta) values ('Tengo sueño',123);
insert into TarjetaEntity (nombre, numeroTarjeta) values ('Belanova',124);
insert into TarjetaEntity (nombre, numeroTarjeta) values ('Cesar',125);

insert into CuentaEntity (id, renta) values (45,123);
insert into CuentaEntity (id, renta) values (46,123);
insert into CuentaEntity (id, renta) values (47,123);

insert into OrdenPagoEntity (idPago, precio, pagada) values (2,24,1);
insert into OrdenPagoEntity (idPago, precio, pagada) values (3,25,0);
insert into OrdenPagoEntity (idPago, precio, pagada) values (4,76,1);
insert into OrdenPagoEntity (idPago, precio, pagada) values (5,23,0);

insert into TorreEntity (id, disponible) values (1, 1);
insert into TorreEntity (id, disponible) values (2,0);
insert into TorreEntity (id, disponible) values (3,0);
insert into TorreEntity (id, disponible) values (4,0);
insert into TorreEntity (id, disponible) values (5,0);

insert into PisoEntity (id, disponible, torre_id) values(1, 0,1); 
insert into PisoEntity (id, disponible, torre_id) values(2, 1, 1);
insert into PisoEntity (id, disponible, torre_id) values(3, 1, 1);
insert into PisoEntity (id, disponible, torre_id) values(4, 1, 1);
insert into PisoEntity (id, disponible, torre_id) values(5, 1, 1);
insert into PisoEntity (id, disponible, torre_id) values(6, 0, 1);
insert into PisoEntity (id, disponible, torre_id) values(7, 0, 1);
insert into PisoEntity (id, disponible, torre_id) values(8, 1, 1);

insert into PisoEntity (id, disponible, torre_id) values(9, 1, 2);
insert into PisoEntity (id, disponible, torre_id) values(10, 0, 2);
insert into PisoEntity (id, disponible, torre_id) values(11, 0, 2);
insert into PisoEntity (id, disponible, torre_id) values(12, 1, 2);

insert into PisoEntity (id, disponible, torre_id) values(13, 1, 3);
insert into PisoEntity (id, disponible, torre_id) values(14, 0, 3);
insert into PisoEntity (id, disponible, torre_id) values(15, 0, 3);



insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('AA', 1, 1,1); 
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('BB', 1, 2,1);
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('CC',0, 3,2); 
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('AA', 1, 4,3); 
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('BB', 1, 5,4);
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('CC',0, 6,5); 
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('AA', 1, 7,5); 
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('BB', 1, 8,5);
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('CC',0, 9,6); 
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('AA', 1, 10,6); 
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('BB', 1, 11,7);
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('CC',0, 12,7); 
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('AA', 1, 13,8); 
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('BB', 1, 14,9);
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('CC',0, 15,10); 
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('AA', 1, 16,11); 
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('BB', 1, 17,12);
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('CC',0, 18,13); 
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('AA', 1, 19,14); 
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('BB', 1, 20,15);
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('CC',0, 21,15); 

insert into EstudianteEntity (documento,nombre,password,username) values (12345678,'Diego','D123','Diego23HT');
insert into EstudianteEntity (documento,nombre,password,username) values (87654321,'Luna','L123','Luna4523HTA');
insert into SugerenciaEntity (id,mensaje,administrador_documento,estudiante_documento) values(1,'Mal servicio',100,12345678); 
insert into SugerenciaEntity (id,mensaje,administrador_documento,estudiante_documento) values(2,'La mechita campeon',100,87654321);
 
select * from EstudianteEntity;
