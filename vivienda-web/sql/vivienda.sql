delete from ApartamentoEntity;
delete from PisoEntity;
delete from TorreEntity;
delete from OrdenPagoEntity;

delete from TarjetaEntity;
delete from MensajeEntity;
delete from SugerenciaEntity;
delete from EstudianteEntity;
delete from CuentaEntity;
delete from ADMINISTRADORENTITY_SUGERENCIAENTITY;
delete from AdministradorEntity;
delete from ServicioEntity;
delete from PrestadorEntity;

insert into PrestadorEntity (nombre, documento, disponible) values ('DesarrolloServicios', 101010, 1);
insert into PrestadorEntity (nombre, documento, disponible) values ('CesarServices', 303030, 1);
insert into ServicioEntity (nombre, Precio) values('Limpieza', 1000);
insert into ServicioEntity (nombre, precio) values('alimentaciÃ³n', 2000);
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

insert into PisoEntity (id, disponible, torre_id) values(101, 0,1); 
insert into PisoEntity (id, disponible, torre_id) values(102, 1, 1);
insert into PisoEntity (id, disponible, torre_id) values(103, 1, 1);
insert into PisoEntity (id, disponible, torre_id) values(104, 1, 1);
insert into PisoEntity (id, disponible, torre_id) values(105, 1, 1);
insert into PisoEntity (id, disponible, torre_id) values(106, 0, 1);
insert into PisoEntity (id, disponible, torre_id) values(107, 0, 1);
insert into PisoEntity (id, disponible, torre_id) values(108, 1, 1);

insert into PisoEntity (id, disponible, torre_id) values(201, 1, 2);
insert into PisoEntity (id, disponible, torre_id) values(202, 0, 2);
insert into PisoEntity (id, disponible, torre_id) values(203, 0, 2);
insert into PisoEntity (id, disponible, torre_id) values(204, 1, 2);

insert into PisoEntity (id, disponible, torre_id) values(301, 1, 3);
insert into PisoEntity (id, disponible, torre_id) values(302, 0, 3);
insert into PisoEntity (id, disponible, torre_id) values(303, 0, 3);

insert into PisoEntity (id, disponible, torre_id) values(401, 1, 4);
insert into PisoEntity (id, disponible, torre_id) values(402, 0, 4);
insert into PisoEntity (id, disponible, torre_id) values(403, 0, 4);
insert into PisoEntity (id, disponible, torre_id) values(404, 0, 4);

insert into PisoEntity (id, disponible, torre_id) values(501, 1, 5);
insert into PisoEntity (id, disponible, torre_id) values(502, 0, 5);
insert into PisoEntity (id, disponible, torre_id) values(503, 0, 5);
insert into PisoEntity (id, disponible, torre_id) values(504, 0, 5);






insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('AA', 1, 10101,101); 
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('BB', 1, 10102,101);
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('CC',0, 10203,102); 



insert into EstudianteEntity (documento,nombre,password,username) values (12345678,'Diego','D123','Diego23HT');
insert into EstudianteEntity (documento,nombre,password,username) values (87654321,'Luna','L123','Luna4523HTA');
insert into SugerenciaEntity (id,mensaje) values(1,'Mal servicio'); 
insert into SugerenciaEntity (id,mensaje) values(2,'La mechita campeon');
 