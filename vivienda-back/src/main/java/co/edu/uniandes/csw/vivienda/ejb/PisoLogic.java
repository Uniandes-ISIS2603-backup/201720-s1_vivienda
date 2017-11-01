/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.ejb;

import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.entities.PisoEntity;
import co.edu.uniandes.csw.vivienda.persistence.PisoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.csw.vivienda.entities.ApartamentoEntity; 
import javax.websocket.server.PathParam;
import javax.ws.rs.Path;

/**
 *
 * @author da.solano1
 */
@Stateless
public class PisoLogic {
    
    @Inject
    private PisoPersistence persistence;  
    
    private ApartamentoLogic apartamentoLogic; 
    
    private PisoLogic pisoLogic; 
    
    public PisoEntity createPiso(PisoEntity piso) throws BusinessLogicException{
        if(persistence.find(piso.getId())!=null)
            throw new BusinessLogicException("El piso con el id\"" + piso.getId() + "ya existe\"");
        persistence.create(piso); 
        return piso; 
    }
    public List<PisoEntity> getPisos(){
        List<PisoEntity> pisos = persistence.findAll(); 
        return pisos; 
    }
    public PisoEntity getPiso(Integer id){
        return persistence.find(id); 
    }
    public PisoEntity updatePiso(PisoEntity nuevo){
        return persistence.update(nuevo);
    }
    public void deletePiso(PisoEntity piso){
        Integer id = piso.getId(); 
        persistence.delete(id);
    }
    /*
    Metodos de apartamentos 
    */
    public ApartamentoEntity createApartamento(ApartamentoEntity apartamento, Integer pisoId) throws BusinessLogicException{
        PisoEntity pisoEntity = getPiso(pisoId); 
        ApartamentoEntity apartamentoEntity = apartamentoLogic.createApartamento(apartamento); 
        List<ApartamentoEntity> lista = pisoEntity.getApartamentos(); 
        lista.add(apartamento); 
        pisoEntity.setApartamentos(lista);
        return apartamentoEntity; 
    }
    public void deleteApartamento(Integer pisoId, Integer apartamentoId){
        PisoEntity pisoEntity = getPiso(pisoId); 
        ApartamentoEntity apartamentoEntity = apartamentoLogic.getApartamento(pisoId); 
        List<ApartamentoEntity> lista = pisoEntity.getApartamentos(); 
        lista.remove(apartamentoEntity); 
        pisoEntity.setApartamentos(lista);
    }
     public ApartamentoEntity updatePiso(Integer pisoId, ApartamentoEntity entityNueva){
        PisoEntity pisoEntity = getPiso(pisoId); 
        ApartamentoEntity entityVieja = apartamentoLogic.getApartamento(entityNueva.getNumApartamento());
        ApartamentoEntity entityNew = apartamentoLogic.updateApartamento(entityNueva); 
        List<ApartamentoEntity> lista = pisoEntity.getApartamentos(); 
        lista.remove(entityVieja); 
        pisoEntity.setApartamentos(lista);
      return entityNew; 
     }
      public List<ApartamentoEntity> getApartamentos(Integer pisoId){
        PisoEntity piso = pisoLogic.getPiso(pisoId);
        PisoEntity pisoEntity = getPiso(pisoId);
        List<ApartamentoEntity> lista = piso.getApartamentos();
      return lista; 
    }
    
    
}
