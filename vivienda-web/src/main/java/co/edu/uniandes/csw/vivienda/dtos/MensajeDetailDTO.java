/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.MensajeEntity;

/**
 *
 * @author da.ramirezv
 */
public class MensajeDetailDTO extends MensajeDTO{
    
    private AdministradorDTO admin;
    
    public MensajeDetailDTO() {
    }
    
    public MensajeDetailDTO(MensajeEntity entity) {
        super(entity);
        admin = new AdministradorDTO(entity.getAdministrador());
    }

    /**
     * @return the admin
     */
    public AdministradorDTO getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(AdministradorDTO admin) {
        this.admin = admin;
    }
    
    
    @Override
    public MensajeEntity toEntity() {
        MensajeEntity mensajeE = super.toEntity();
        mensajeE.setAdministrador(this.admin.toEntity());
        return mensajeE;
    }
}