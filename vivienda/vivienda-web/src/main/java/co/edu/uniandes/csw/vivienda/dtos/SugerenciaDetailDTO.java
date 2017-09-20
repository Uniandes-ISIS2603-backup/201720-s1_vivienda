/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.SugerenciaEntity;

/**
 *
 * @author e.reyesm
 */
public class SugerenciaDetailDTO extends SugerenciaDTO {
         /**
     * Constructor por defecto
     */
    public SugerenciaDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public SugerenciaDetailDTO(SugerenciaEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public SugerenciaEntity toEntity() {
        SugerenciaEntity sugerenciaE = super.toEntity();
        return sugerenciaE;
    }
}
