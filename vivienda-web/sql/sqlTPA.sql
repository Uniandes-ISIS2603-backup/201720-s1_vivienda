/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  da.solano1
 * Created: 25/09/2017
 */
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





