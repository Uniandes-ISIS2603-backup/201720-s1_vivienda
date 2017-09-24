/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.ejb;

import co.edu.uniandes.csw.vivienda.entities.MensajeEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.MensajePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author da.ramirezv
 */
@Stateless
public class MensajeLogic {
    
    @Inject
    private MensajePersistence persistence;
    
    public MensajeEntity createMensaje(MensajeEntity entity) throws BusinessLogicException {
        if (persistence.findByName(entity.getTitulo()) != null) {
            throw new BusinessLogicException("Ya existe una mensaje con el nombre \"" + entity.getTitulo() + "\"");
        }
        persistence.create(entity);
        return entity;
    }
    
    public List<MensajeEntity> getMensajes() {
        List<MensajeEntity> mensajes = persistence.findAll();
        return mensajes;
    }
    
    public MensajeEntity getMensaje(Long id) {
        MensajeEntity mensaje = persistence.findByID(id);
        return mensaje;
    }
    
    public MensajeEntity updateMensaje(Long id, MensajeEntity entity) {
        MensajeEntity newEntity = persistence.update(entity);
        return newEntity;
    }
    
    public void deleteMensaje(Long id) {
        persistence.delete(id);
    }
}
