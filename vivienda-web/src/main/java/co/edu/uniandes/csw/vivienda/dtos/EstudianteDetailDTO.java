/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.EstudianteEntity;

/**
 *
 * @author e.reyesm
 */
public class EstudianteDetailDTO extends EstudianteDTO {
     /**
     * Constructor por defecto
     */
    public EstudianteDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public EstudianteDetailDTO(EstudianteEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public EstudianteEntity toEntity() {
        EstudianteEntity estudianteE = super.toEntity();
        return estudianteE;
    }

}
