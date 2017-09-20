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
public class SugerenciaDTO {
   
    private Long id;
    private String mensaje;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
     /**
     * Constructor por defecto
     */
    public SugerenciaDTO() {
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param sugerencia: Es la entidad que se va a convertir a DTO
     */
    public SugerenciaDTO(SugerenciaEntity sugerencia) {
        
        this.id = sugerencia.getId();
        this.mensaje = sugerencia.getMensaje();
    }

    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public SugerenciaEntity toEntity() {
        SugerenciaEntity entity = new SugerenciaEntity();
        entity.setId(this.id);
        entity.setMensaje(this.mensaje);
        return entity;
    }
    
    
}
