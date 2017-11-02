/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.TorreEntity;

/**
 *
 * @author da.solano1
 */
public class TorreDTO {
    private Integer id; 
    private boolean disponible; 
    
    public TorreDTO(){
        //Constructor vacío, utilizar el otro
        
    }
    public TorreDTO(TorreEntity torre){
      this.id = torre.getId(); 
      this.disponible = torre.isDisponible(); 
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    public TorreEntity toEntity(){
        TorreEntity entity = new TorreEntity(); 
        entity.setId(this.id);
        entity.setDisponible(this.disponible);
        return entity; 
    }
}
