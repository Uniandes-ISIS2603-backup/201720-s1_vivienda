/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.ejb;


import co.edu.uniandes.csw.vivienda.entities.ApartamentoEntity;
import co.edu.uniandes.csw.vivienda.persistence.ApartamentoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException; 


/**
 *
 * @author da.solano1
 */
@Stateless
public class ApartamentoLogic {
    @Inject
    private ApartamentoPersistence persistence;
    
     /**
     * Se encarga de crear un Apartamento en la base de datos.
     * @param apartamento Objeto de ApartamentoEntity con los datos nuevos.
     * @return Objeto de ApartamentoEntity con los datos nuevos.
     * @generated
     */
    
    public ApartamentoEntity createApartamento(ApartamentoEntity apartamento) throws BusinessLogicException{
       if(persistence.find(apartamento.getNumApartamento())!= null)
           throw new BusinessLogicException("El apartamento con el numero\"" + apartamento.getNumApartamento()+ "ya existe\""); 
       persistence.create(apartamento); 
       return apartamento; 
    }
    
     /**
     * Obtiene la lista de los registros de Apartamento.
     * @return Colección de objetos de ApartamentoEntity.
     * @generated
     */
    public List<ApartamentoEntity> getApartamentos(){
        List<ApartamentoEntity> apartamentos = persistence.findAll(); 
        return apartamentos; 
    }
    
    /**
     * Obtiene los datos de una instancia de Apartamento a partir de su numero de apartamento.
     * @param numApar Identificador de la instancia a consultar.
     * @return Instancia de ApartamentoEntity con los datos del Apartamento consultado.
     * @generated
     */
    public ApartamentoEntity getApartamento(Integer numApar){
        return persistence.find(numApar); 
    }
    
    /**
     * Actualiza la información de una instancia de Apartamento.
     * @param nuevo Instancia de ApartamentoEntity con los nuevos datos.
     * @return Instancia de ApartamentoEntity con los datos actualizados.
     * @generated
     */
    public ApartamentoEntity updateApartamento(ApartamentoEntity nuevo){
        return persistence.update(nuevo); 
    }
    
    /**
     * Elimina una instancia de Apartamento de la base de datos.
     * @param apartamento Instancia de ApartamentoEntity a borrar.
     * @generated
     */
    public void deleteApartamento(ApartamentoEntity apartamento){
        Integer numApar = apartamento.getNumApartamento(); 
        persistence.delete(numApar);
    }
}
