/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.PrestadorEntity;


/**
 *
 * @author rj.gonzalez10
 */
public class PrestadorDetailDTO extends PrestadorDTO {
    public PrestadorDetailDTO(){}
    
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public PrestadorDetailDTO(PrestadorEntity entity) {
        super(entity);}
     /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public PrestadorEntity toEntity() {
        PrestadorEntity cityE = super.toEntity();
        return cityE;
}
}