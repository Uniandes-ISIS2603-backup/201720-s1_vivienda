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
public class MensajeDetailDTO extends MensajeDTO {

    /**
     * variables de clase
     */
    private AdministradorDTO admin;

    /**
     * constructor vacio
     */
    public MensajeDetailDTO() {
        //Constructor vacío, utilizar el otro
    }

    /**
     * contructor del detail
     * @param entity 
     */
    public MensajeDetailDTO(MensajeEntity entity) {
        super(entity);

        if (entity != null && entity.getAdministrador() != null ) {

            admin = new AdministradorDTO(entity.getAdministrador());
        }

    }

    /**
     * pide el administrador
     * @return the admin
     */
    public AdministradorDTO getAdmin() {
        return admin;
    }

    /**
     * pone un nuevo administrador
     * @param admin the admin to set
     */
    public void setAdmin(AdministradorDTO admin) {
        this.admin = admin;
    }

    /**
     * devuelve un mensajeEntity
     * @return 
     */
    @Override
    public MensajeEntity toEntity() {
        MensajeEntity mensajeE = super.toEntity();
        if (this.admin != null) {

            mensajeE.setAdministrador(this.admin.toEntity());

        }
        return mensajeE;
    }
}
