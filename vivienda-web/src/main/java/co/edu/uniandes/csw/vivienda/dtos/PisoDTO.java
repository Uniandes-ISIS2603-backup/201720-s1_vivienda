/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.PisoEntity;
import java.util.List;

/**
 *
 * @author da.solano1
 */
public class PisoDTO {
    private Integer id; 
    private boolean disponible; 
    private TorreDTO torre; 

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

    public TorreDTO getTorre() {
        return torre;
    }

    public void setTorre(TorreDTO torre) {
        this.torre = torre;
    }
    public PisoDTO(PisoEntity entity){
        this.id = entity.getId(); 
        this.disponible = entity.isDisponible(); 
        this.torre = new TorreDTO(entity.getTorre());
    }
   
    public PisoEntity toEntity(){
        PisoEntity piso = new PisoEntity(); 
        piso.setId(this.id);
        piso.setDisponible(this.disponible);
        piso.setTorre(this.torre.toEntity());
        return piso; 
    }
}
