delete from AdministradorEntity;
delete from TarjetaEntity;
delete from CuentaEntity;
delete from OrdenPagoEntity;


insert into AdministradorEntity (documento, nombre, username, password) values (100,'David', 'DavidCuenta,', '123hue');
insert into AdministradorEntity (documento, nombre, username, password) values (200,'David', 'DavidCuenta,', '123hue');

delete from MensajeEntity;

insert into MensajeEntity (id, titulo, asunto, mensaje) values (100,'ErrorRaro', 'Hubo un error raro,', 'Error raro');
insert into MensajeEntity (id, titulo, asunto, mensaje) values (200,'Muerto', 'Hubo un muerto,', 'Alguien murio');

<<<<<<< HEAD


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
=======
delete from TorreEntity; 
delete from ApartamentoEntity;
delete from PisoEntity; 
insert into TorreEntity (id, disponible) values (1, 1);
insert into TorreEntity (id, disponible) values (2,0);
insert into PisoEntity (id, disponible, torre_id) values(1, 0,1); 
insert into PisoEntity (id, disponible, torre_id) values(2, 1, 1);
insert into PisoEntity (id, disponible, torre_id) values(3, 1, 2);
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('AA', 1, 1,1); 
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('BB', 1, 2,1);
insert into ApartamentoEntity (categoria, disponible, numApartamento, piso_id) values('CC',0, 3,2); 

/*delete from EstudianteEntity; 
delete from SugerenciaEntity;

insert into EstudianteEntity (documento,nombre,password,username,) values (12345678,'Diego','D123','Diego23HT');
insert into EstudianteEntity (documento,nombre,password,username,) values (87654321,'Luna','L123','Luna4523HTA');
insert into SugerenciaEntity (id,mensaje) values(1,'Mal servicio'); 
insert into SugerenciaEntity (id,mensaje) values(2,'La mechita campeon');*/ 
 
>>>>>>> estebanNueva