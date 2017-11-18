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
    
     /**
     * Se encarga de crear un Mensaje en la base de datos.
     * @param entity Objeto de MensajeEntity con los datos nuevos.
     * @return Objeto de MensajeEntity con los datos nuevos.
     * @generated
     */
    
    public MensajeEntity createMensaje(MensajeEntity entity) throws BusinessLogicException {
        if (persistence.findByName(entity.getTitulo()) != null) {
            throw new BusinessLogicException("Ya existe una mensaje con el nombre \"" + entity.getTitulo() + "\"");
        }
        persistence.create(entity);
        return entity;
    }
    
      /**
     * Obtiene la lista de los registros de Mensaje.
     * @return Colección de objetos de MensajeEntity.
     * @generated
     */
    
    public List<MensajeEntity> getMensajes() {
        List<MensajeEntity> mensajes = persistence.findAll();
        return mensajes;
    }
    
     /**
     * Obtiene los datos de una instancia de Mensaje a partir de su ID.
     * @param id Identificador de la instancia a consultar.
     * @return Instancia de MensajeEntity con los datos del Mensaje consultado.
     * @generated
     */
    
    public MensajeEntity getMensaje(Long id) {
        MensajeEntity mensaje = persistence.findByID(id);
        return mensaje;
    }
    
     /**
     * Actualiza la información de una instancia de Mensaje.
     * @param entity Instancia de MensajeEntity con los nuevos datos.
     * @return Instancia de MensajeEntity con los datos actualizados.
     * @generated
     */
    
    public MensajeEntity updateMensaje(Long id, MensajeEntity entity) {
        MensajeEntity newEntity = persistence.update(entity);
        return newEntity;
    }
    
    /**
     * Elimina una instancia de Mensaje de la base de datos.
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    
    public void deleteMensaje(Long id) {
        persistence.delete(id);
    }
}
