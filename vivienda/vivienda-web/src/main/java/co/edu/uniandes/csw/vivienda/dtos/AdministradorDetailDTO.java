/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.AdministradorEntity;
import co.edu.uniandes.csw.vivienda.entities.TorreEntity;
import co.edu.uniandes.csw.vivienda.entities.SugerenciaEntity;
import co.edu.uniandes.csw.vivienda.entities.MensajeEntity;
import java.util.List;

/**
 *
 * @author da.ramirezv
 */
public class AdministradorDetailDTO extends AdminitradorDTO{
    
    private List<MensajeEntity> mensajes;
   
    private List<SugerenciaEntity> sugerencias;
    
    private List<TorreEntity> torres;
    
    public AdministradorDetailDTO() {
    }
    
    public AdministradorDetailDTO(AdministradorEntity entity) {
        super(entity);
        mensajes = entity.getMensajes();
        sugerencias = entity.getSugerencias();
        torres = entity.getTorres();
    }
    
    
    @Override
    public AdministradorEntity toEntity() {
        AdministradorEntity adminE = super.toEntity();
        adminE.setMensajes(this.mensajes);
        adminE.setSugerencias(this.sugerencias);
        adminE.setTorres(this.torres);
        return adminE;
    }
}
