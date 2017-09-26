delete from AdministradorEntity;
delete from TarjetaEntity;
delete from CuentaEntity;
delete from OrdenPagoEntity;


insert into AdministradorEntity (documento, nombre, username, password) values (100,'David', 'DavidCuenta,', '123hue');
insert into AdministradorEntity (documento, nombre, username, password) values (200,'David', 'DavidCuenta,', '123hue');

delete from MensajeEntity;

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