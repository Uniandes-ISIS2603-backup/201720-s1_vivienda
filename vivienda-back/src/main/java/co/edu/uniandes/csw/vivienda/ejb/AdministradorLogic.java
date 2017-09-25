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
    
    
    public AdministradorEntity createAdministrador(AdministradorEntity entity) throws BusinessLogicException {
        if (persistence.findByName(entity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una admin con el nombre \"" + entity.getNombre() + "\"");
        }
        persistence.create(entity);
        return entity;
    }
    
    public List<AdministradorEntity> getAdministradores() {
        List<AdministradorEntity> admins = persistence.findAll();
        return admins;
    }
    
    public AdministradorEntity getAdministrador(Long id) {
        AdministradorEntity admin = persistence.findByID(id);
        return admin;
    }
    
    public AdministradorEntity updateAdministrador(Long id, AdministradorEntity entity) {
        AdministradorEntity newEntity = persistence.update(entity);
        return newEntity;
    }
    
    public void deleteAdministrador(Long id) {
        persistence.delete(id);
    }
}
