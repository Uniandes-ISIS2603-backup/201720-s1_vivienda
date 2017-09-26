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
    
    private EstudianteDTO estudiante;
    private AdministradorDTO administrador;

    public EstudianteDTO getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteDTO estudiante) {
        this.estudiante = estudiante;
    }

    public AdministradorDTO getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorDTO administrador) {
        this.administrador = administrador;
    }
    
    
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
        estudiante = new EstudianteDTO(entity.getEstudiante());
        administrador = new AdministradorDTO(entity.getAdministrador());
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public SugerenciaEntity toEntity() {
        SugerenciaEntity sugerenciaE = super.toEntity();
        sugerenciaE.setAdministrador(this.administrador.toEntity());
        sugerenciaE.setEstudiante(this.estudiante.toEntity());
        return sugerenciaE;
    }
}
