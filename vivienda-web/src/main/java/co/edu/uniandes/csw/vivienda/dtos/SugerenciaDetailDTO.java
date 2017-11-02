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
     /*
     * Constructor por defecto
     */
    private EstudianteDTO estudiante;
    private AdministradorDTO admin;
    
     public SugerenciaDetailDTO() {
        //Constructor vacío, no usar este
        
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public SugerenciaDetailDTO(SugerenciaEntity entity) {
        super(entity);
        
           
        if (entity.getAdministrador() != null) {
            this.admin = new AdministradorDTO(entity.getAdministrador());
        }
        if (entity.getEstudiante() != null) {
            this.estudiante = new EstudianteDTO(entity.getEstudiante());
        }

    }


    public void setAdmin(AdministradorDTO admin) {
        this.admin = admin;
    }

    public void setEstudiante(EstudianteDTO estudiante) {
        this.estudiante = estudiante;
    }

    public AdministradorDTO getAdmin() {
        return admin;
    }

    public EstudianteDTO getEstudiante() {
        return estudiante;
    }

    
    
   
    /**
     * Transformar un DTO a un Entity
     *
     * @return
     */
    @Override
    public SugerenciaEntity toEntity() {
        SugerenciaEntity sugerenciaE = super.toEntity();

        if (admin != null) {
            sugerenciaE.setAdministrador(this.admin.toEntity());
        }
        if (estudiante != null) {
            sugerenciaE.setEstudiante(this.estudiante.toEntity());
        }

        return sugerenciaE;
    }
}
