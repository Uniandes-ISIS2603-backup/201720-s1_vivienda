/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.ejb;

import co.edu.uniandes.csw.vivienda.entities.PisoEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.entities.TorreEntity;
import co.edu.uniandes.csw.vivienda.persistence.TorrePersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author da.solano1
 */
@Stateless
public class TorreLogic {
    
    private static final Logger LOGGER = Logger.getLogger(TorreLogic.class.getName());
    
    @Inject 
    private TorrePersistence persistence; 
    
    private PisoLogic pisoLogic; 
    
    public TorreEntity createTorre(TorreEntity torre)throws BusinessLogicException{
        LOGGER.info("Inicia proceso de creación de city");
        if(persistence.find(torre.getId())!= null)
                throw new BusinessLogicException("La torre con el id \"" + torre.getId()+ "ya existe\"");
        persistence.create(torre); 
        LOGGER.info("Termina proceso de creación de la torre");
        return torre; 
    }
    public List<TorreEntity> getTorres(){
        LOGGER.info("Inicia proceso de consultar todas las torres");
        List<TorreEntity> torres = persistence.findAll(); 
        LOGGER.info("Termina proceso de consultar todas las cities");
        return torres;
    }
    public TorreEntity getTorre(Integer id){
        return persistence.find(id); 
    }
    public TorreEntity updateTorre(TorreEntity nueva){
        return persistence.update(nueva); 
    }
    public void delete(TorreEntity nueva){
        Integer id = nueva.getId(); 
        persistence.delete(id);
    }
    public PisoEntity createPiso(PisoEntity piso, Integer torreId) throws BusinessLogicException{
        TorreEntity torreEntity = getTorre(torreId);
        PisoEntity pisoEntity = pisoLogic.createPiso(piso);
        List<PisoEntity> lista = torreEntity.getPisos();
        lista.add(piso); 
        torreEntity.setPisos(lista);
        return pisoEntity; 
    }
    public void deletePiso(Integer pisoId, Integer torreId){
        TorreEntity torreEntity = getTorre(torreId); 
        PisoEntity piso = pisoLogic.getPiso(pisoId); 
        List<PisoEntity> lista = torreEntity.getPisos(); 
        lista.remove(piso); 
        torreEntity.setPisos(lista);
    }
    public PisoEntity updatePiso(Integer torreId, PisoEntity entityNueva){
        TorreEntity torreEntity = getTorre(torreId); 
        PisoEntity entityVieja = pisoLogic.getPiso(entityNueva.getId());
        PisoEntity entityNew = pisoLogic.updatePiso(entityNueva); 
        List<PisoEntity> lista = torreEntity.getPisos(); 
        lista.remove(entityVieja); 
        torreEntity.setPisos(lista);
      return entityNew; 
    }
    
    public List<PisoEntity> getPisos(Integer torreId){
        TorreEntity torreEntity = getTorre(torreId);
        List<PisoEntity> lista = torreEntity.getPisos();
      return lista; 
    }
    
    
    
}
