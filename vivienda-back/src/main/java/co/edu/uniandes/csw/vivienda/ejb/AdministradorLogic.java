/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.ejb;

import co.edu.uniandes.csw.vivienda.entities.AdministradorEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.AdministradorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author da.ramirezv
 */
@Stateless
public class AdministradorLogic {
    
    @Inject
    private AdministradorPersistence persistence;
    
    /**
     * Se encarga de crear un Administrador en la base de datos.
     * @param entity Objeto de AdministradorEntity con los datos nuevos.
     * @return Objeto de AdministradorEntity con los datos nuevos.
     * @generated
     */
    
    public AdministradorEntity createAdministrador(AdministradorEntity entity) throws BusinessLogicException {
        if (persistence.findByName(entity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una admin con el nombre \"" + entity.getNombre() + "\"");
        }
        persistence.create(entity);
        return entity;
    }
    
     /**
     * Obtiene la lista de los registros de Administrador.
     * @return Colección de objetos de AdministradorEntity.
     * @generated
     */
    
    public List<AdministradorEntity> getAdministradores() {
        List<AdministradorEntity> admins = persistence.findAll();
        return admins;
    }
    
     /**
     * Obtiene los datos de una instancia de Administrador a partir de su ID.
     * @param id Identificador de la instancia a consultar.
     * @return Instancia de AdministradorEntity con los datos del Administrador consultado.
     * @generated
     */
    
    public AdministradorEntity getAdministrador(Long id) {
        AdministradorEntity admin = persistence.findByID(id);
        return admin;
    }
    
    /**
     * Actualiza la información de una instancia de Estudiante.
     * @param entity Instancia de AdministradorEntity con los nuevos datos.
     * * @param id ID del Administrador a actualizar.
     * @return Instancia de AdministradorEntity con los datos actualizados.
     * @generated
     */
    
    public AdministradorEntity updateAdministrador(Long id, AdministradorEntity entity) {
        AdministradorEntity newEntity = persistence.update(entity);
        return newEntity;
    }
    
     /**
     * Elimina una instancia de Administrador de la base de datos.
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    
    public void deleteAdministrador(Long id) {
        persistence.delete(id);
    }
}
