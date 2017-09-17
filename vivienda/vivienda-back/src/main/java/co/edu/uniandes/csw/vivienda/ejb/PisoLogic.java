/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.ejb;

import co.edu.uniandes.csw.vivienda.entities.PisoEntity;
import co.edu.uniandes.csw.vivienda.persistence.PisoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author da.solano1
 */
@Stateless
public class PisoLogic {
    
    @Inject
    private PisoPersistence persistence; 
    
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
    
}
