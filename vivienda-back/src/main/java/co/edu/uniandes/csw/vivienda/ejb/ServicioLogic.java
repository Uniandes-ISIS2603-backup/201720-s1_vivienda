/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.ejb;

import co.edu.uniandes.csw.vivienda.entities.ServicioEntity;
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

  public ServicioEntity createServicio(ServicioEntity entity) throws Exception {
        LOGGER.info("Inicia proceso de creación de Servicio");
        // Verifica la regla de negocio que dice que no puede haber dos cityes con el mismo nombre
        if (persistence.findByName(entity.getNombre()) != null) {
            throw new Exception("Ya existe un servicio con el nombre \"" + entity.getNombre() + "\"");
        }
        // Invoca la persistencia para crear al servicio
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de city");
        return entity;
    }

    public List<ServicioEntity> getServicios() {
        LOGGER.info("Inicia proceso de consultar todos los servicios");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ServicioEntity> services = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los servicios");
        return services;
    }
    // Método de la capa lógica que permite eliminar un servición por su id
    public ServicioEntity getServicio(String id) {
        ServicioEntity city = persistence.findByName(id);
        return city;
    }
    // método de lógica que permine actualizar algún servicio

    public ServicioEntity updateCity(String id, ServicioEntity entity) throws Exception {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar servicio con id={0}", id);
       /** if (persistence.existCityWithSameNameandDifferentId(id, entity.getName)) {
            throw new Exception("Ya existe un Servicio con ese nombre "+entity.getNombre());
        }
        * */
        ServicioEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar servicio con id={0}", entity.getNombre());
        return newEntity;
    }
    // método de lógica que llama a la persistencia y le pide eliminar un servicio
    public void deleteCity(ServicioEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar proceso con id={0}", entity.getNombre());
        persistence.delete(entity.getNombre());
         LOGGER.log(Level.INFO, "Termina proceso de borrar proceso con id={0}", entity.getNombre());
        
    }

}