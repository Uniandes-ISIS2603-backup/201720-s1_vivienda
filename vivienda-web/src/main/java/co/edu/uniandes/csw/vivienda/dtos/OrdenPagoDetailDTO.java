/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.OrdenPagoEntity;

/**
 *
 * @author e.reyesm
 */
public class OrdenPagoDetailDTO extends OrdenPagoDTO{
    
    ServicioDTO servicio;

    /**
     * Constructor por defecto
     */
    public OrdenPagoDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public OrdenPagoDetailDTO(OrdenPagoEntity entity) {
        super(entity);
        if(entity.getServicio()!=null){
        this.servicio = new ServicioDTO(entity.getServicio());
        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public OrdenPagoEntity toEntity() {
        OrdenPagoEntity ordenPagoE = super.toEntity();
        if(this.servicio!=null)
        {
            ordenPagoE.setServicio(this.servicio.toEntity());
        }
        return ordenPagoE;
    }

     public ServicioDTO getServicio() {
        return servicio;
    }

    public void setServicio(ServicioDTO servicio) {
        this.servicio = servicio;
    }
}
