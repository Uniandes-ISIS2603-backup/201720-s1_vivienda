/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.ejb;

import co.edu.uniandes.csw.vivienda.entities.PrestadorEntity;
import co.edu.uniandes.csw.vivienda.entities.ServicioEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.PrestadorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author rj.gonzalez10
 */
@Stateless
public class PrestadorLogic {
     private final static Logger LOGGER =  Logger.getLogger(ServicioLogic.class.getName());
@Inject
private PrestadorPersistence persistence; //variable para accceder a la persitencia de servicio

    /**
     * Se encarga de crear un Prestador en la base de datos.
     * @param entity Objeto de PrestadorEntity con los datos nuevos.
     * @return Objeto de PrestadorEntity con los datos nuevos.
     * @generated
     */

  public PrestadorEntity createServicio(PrestadorEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de un prestador");
        // Verifica la regla de negocio que dice que no puede haber dos cityes con el mismo nombre
        if (persistence.findByName(entity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe un Prestador con el nombre \"" + entity.getNombre() + "\"");
        }
        // Invoca la persistencia para crear al servicio
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de city");
        return entity;
    }
  
    /**
     * Obtiene la lista de los registros de Prestador.
     * @return Colección de objetos de PrestadorEntity.
     * @generated
     */

    public List<PrestadorEntity> getServicios() {
        LOGGER.info("Inicia proceso de consultar todos los servicios");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<PrestadorEntity> services = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los servicios");
        return services;
    }
    
    /**
     * Obtiene los datos de una instancia de Prestador a partir de su ID.
     * @param id Identificador de la instancia a consultar.
     * @return Instancia de PrestadorEntity con los datos del Prestador consultado.
     * @generated
     */
    
    public PrestadorEntity getServicio(Long id) {
        PrestadorEntity city = persistence.findByID(id);
        return city;
    }
    
    /**
     * Actualiza la información de una instancia de Prestador.
     * @param entity Instancia de PrestadorEntity con los nuevos datos.
     * @return Instancia de PrestadorEntity con los datos actualizados.
     * @generated
     */

    public PrestadorEntity updatePrestador(Long id, PrestadorEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar prestador con id={0}", id);
       /** if (persistence.existCityWithSameNameandDifferentId(id, entity.getName)) {
            throw new Exception("Ya existe un Servicio con ese nombre "+entity.getNombre());
        }
        * */
        PrestadorEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar prestador con id={0}", entity.getNombre());
        return newEntity;
    }
    
     /**
     * Elimina una instancia de Prestador de la base de datos.
     * @param entity Instancia a eliminar.
     * @generated
     */
    
    public void deletePrestador(PrestadorEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar proceso con id={0}", entity.getNombre());
        persistence.delete(entity.getDocumento());
         LOGGER.log(Level.INFO, "Termina proceso de borrar proceso con id={0}", entity.getNombre());
        
    }
}
