/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.PisoEntity;

/**
 *
 * @author da.solano1
 */
public class PisoDTO {

    private Integer id;
    private boolean disponible;

    public PisoDTO() {
        //Constructor vacío, utilizar el otro
    }

    public PisoDTO(PisoEntity entity) {
        this.id = entity.getId();
        this.disponible = entity.isDisponible();
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

    public PisoEntity toEntity() {
        PisoEntity piso = new PisoEntity();
        piso.setId(this.id);
        piso.setDisponible(this.disponible);
        return piso;
    }
}
