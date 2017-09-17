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

/**
 *
 * @author da.solano1
 */
@Stateless
public class ApartamentoLogic {
    @Inject
    private ApartamentoPersistence persistence; 
    
    public ApartamentoEntity createApartamento(ApartamentoEntity apartamento) throws BusinessLogicException{
       if(persistence.find(apartamento.getNumApartamento())!= null)
           throw new BusinessLogicException("El apartamento con el numero\"" + apartamento.getNumApartamento()+ "ya existe\""); 
       persistence.create(apartamento); 
       return apartamento; 
    }
    public List<ApartamentoEntity> getApartamentos(){
        List<ApartamentoEntity> apartamentos = persistence.findAll(); 
        return apartamentos; 
    }
    public ApartamentoEntity getApartamento(Integer numApar){
        return persistence.find(numApar); 
    }
    public ApartamentoEntity updateApartamento(ApartamentoEntity nuevo){
        return persistence.update(nuevo); 
    }
    public void deleteApartamento(ApartamentoEntity apartamento){
        Integer numApar = apartamento.getNumApartamento(); 
        persistence.delete(numApar);
    }
}
