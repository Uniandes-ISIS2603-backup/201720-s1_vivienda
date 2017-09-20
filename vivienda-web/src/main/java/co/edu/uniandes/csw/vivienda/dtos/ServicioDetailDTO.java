/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.ServicioEntity;

/**
 *
 * @author rj.gonzalez10
 */
public class ServicioDetailDTO extends ServicioDTO{
    
    public ServicioDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public ServicioDetailDTO(ServicioEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public ServicioEntity toEntity() {
        ServicioEntity cityE = super.toEntity();
        return cityE;
    }
}
