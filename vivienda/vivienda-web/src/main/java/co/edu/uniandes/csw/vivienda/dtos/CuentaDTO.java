/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.CuentaEntity;

/**
 *
 * @author mp.franco10
 */
public class CuentaDTO {
    
    private Long id;
    private int renta;


    /**
     * Constructor por defecto
     */
    public CuentaDTO() {
    }

    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param cuenta: Es la entidad que se va a convertir a DTO 
     */
    public CuentaDTO(CuentaEntity cuenta) {
        this.id = cuenta.getId();
        this.renta = cuenta.getRenta();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRenta() {
        return renta;
    }

    public void setRenta(int renta) {
        this.renta = renta;
    }

    
     /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO 
     */
    public CuentaEntity toEntity() {
        CuentaEntity entity = new CuentaEntity();
        entity.setId(this.id);
        entity.setRenta(this.renta);
        return entity;
    }
}
