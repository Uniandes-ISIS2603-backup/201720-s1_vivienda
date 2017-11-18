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
    
    /**
     * Se encarga de crear un Piso en la base de datos.
     * @param piso Objeto de PisoEntity con los datos nuevos.
     * @return Objeto de PisoEntity con los datos nuevos.
     * @generated
     */
    
    public PisoEntity createPiso(PisoEntity piso) throws BusinessLogicException{
        if(persistence.find(piso.getId())!=null)
            throw new BusinessLogicException("El piso con el id\"" + piso.getId() + "ya existe\"");
        persistence.create(piso); 
        return piso; 
    }
    
    /**
     * Obtiene la lista de los registros de Piso.
     * @return Colección de objetos de PisoEntity.
     * @generated
     */
    public List<PisoEntity> getPisos(){
        List<PisoEntity> pisos = persistence.findAll(); 
        return pisos; 
    }
    
    /**
     * Obtiene los datos de una instancia de Piso a partir de su ID.
     * @param id Identificador de la instancia a consultar.
     * @return Instancia de PisoEntity con los datos del Piso consultado.
     * @generated
     */
    public PisoEntity getPiso(Integer id){
        return persistence.find(id); 
    }
    
    /**
     * Actualiza la información de una instancia de Piso.
     * @param nuevo Instancia de PisoEntity con los nuevos datos.
     * @return Instancia de PisoEntity con los datos actualizados.
     * @generated
     */
    public PisoEntity updatePiso(PisoEntity nuevo){
        return persistence.update(nuevo);
    }
    
     /**
     * Elimina una instancia de Piso de la base de datos.
     * @param piso Instancia a eliminar.
     * @generated
     */
    public void deletePiso(PisoEntity piso){
        Integer id = piso.getId(); 
        persistence.delete(id);
    }
    
    /*
    Metodos de apartamentos// Para crear las relaciones.
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
