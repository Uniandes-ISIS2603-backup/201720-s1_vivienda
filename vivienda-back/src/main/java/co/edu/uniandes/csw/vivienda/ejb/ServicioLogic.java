/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.ejb;

import co.edu.uniandes.csw.vivienda.entities.ServicioEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.ServicioPersistence;
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
public class ServicioLogic {
 private final static Logger LOGGER =  Logger.getLogger(ServicioLogic.class.getName());
@Inject
private ServicioPersistence persistence; //variable para accceder a la persitencia de servicio

  /**
     * Se encarga de crear un Servicio en la base de datos.
     * @param entity Objeto de ServicioEntity con los datos nuevos.
     * @return Objeto de ServicioEntity con los datos nuevos.
     * @generated
     */

  public ServicioEntity createServicio(ServicioEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Servicio");
        // Verifica la regla de negocio que dice que no puede haber dos cityes con el mismo nombre
        if (persistence.findByName(entity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe un servicio con el nombre \"" + entity.getNombre() + "\"");
        }
        // Invoca la persistencia para crear al servicio
        System.err.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.err.println("------------------------"+entity.getMyPrestador().getDocumento());
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de city");
        return entity;
    }
  
    /**
     * Obtiene la lista de los registros de Servicio.
     * @return Colección de objetos de ServicioEntity.
     * @generated
     */

    public List<ServicioEntity> getServicios() {
        LOGGER.info("Inicia proceso de consultar todos los servicios");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ServicioEntity> services = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los servicios");
        return services;
    }
    
    /**
     * Obtiene los datos de una instancia de Servicio a partir de su ID.
     * @param id Identificador de la instancia a consultar.
     * @return Instancia de ServicioEntity con los datos del Servicio consultado.
     * @generated
     */
    
    public ServicioEntity getServicio(String id) {
        ServicioEntity city = persistence.findByName(id);
        return city;
    }
    
    /**
     * Actualiza la información de una instancia de Servicio.
     * @param entity Instancia de ServicioEntity con los nuevos datos.
     * @param id Identificador del Servicio a actualizar.
     * @return Instancia de ServicioEntity con los datos actualizados.
     * @generated
     */

    public ServicioEntity updateCity(String id, ServicioEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar servicio con id={0}", id);
       /** if (persistence.existCityWithSameNameandDifferentId(id, entity.getName)) {
            throw new Exception("Ya existe un Servicio con ese nombre "+entity.getNombre());
        }
        * */
        ServicioEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar servicio con id={0}", entity.getNombre());
        return newEntity;
    }
    
     /**
     * Elimina una instancia de Servicio de la base de datos.
     * @param entity Instancia a eliminar.
     * @generated
     */
    
    public void deleteCity(ServicioEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar proceso con id={0}", entity.getNombre());
        persistence.delete(entity.getNombre());
         LOGGER.log(Level.INFO, "Termina proceso de borrar proceso con id={0}", entity.getNombre());
        
    }

}