/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;

import co.edu.uniandes.csw.vivienda.entities.AdministradorEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author da.ramirezv
 */
@Stateless
public class AdministradorPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(AdministradorPersistence.class.getName());

    @PersistenceContext(unitName = "viviendaPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto administrador que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public AdministradorEntity create(AdministradorEntity entity) {
        LOGGER.info("Creando un administrador nuevo");
        em.persist(entity);
        LOGGER.info("Creando un administrador nuevo");
        return entity;
    }

    /**
     * Busca si hay algun administrador con el nombre que se envía de argumento
     *
     * @param name: Nombre del administrador que se está buscando
     * @return null si no existe ningun administrador con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public AdministradorEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando administrador por nombre", nombre);

        // Se crea un query para buscar cityes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From AdministradorEntity e where e.nombre = :nombre", AdministradorEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<AdministradorEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
    
    /**
     * actualiza un administrador
     * @param entity
     * @return 
     */
     public AdministradorEntity update(AdministradorEntity entity) {
        
        // Se crea un query para buscar cityes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        AdministradorEntity adminreferencia = em.merge(entity);
        // Se remplaza el placeholder ":name" con el valor del argumento
       
        return adminreferencia;
        
    }
     
     /**
      * borra un administrador
      * @param id 
      */
     public void delete(Long id) {
        AdministradorEntity temp = findByID(id);
        em.remove(temp);
     }
       
     
    
    /**
     * busca un administrador por id
     * @param id
     * @return 
     */
    public AdministradorEntity findByID(Long id) {
        LOGGER.log(Level.INFO, "Consultando administrador por id", id);
        // Se crea un query para buscar cityes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        //TypedQuery query = em.createQuery("Select e From CityEntity e where e.id = :id", CityEntity.class);
        AdministradorEntity admin = em.find(AdministradorEntity.class, id);
        // Se remplaza el placeholder ":name" con el valor del argumento
        //query = query.setParameter("id", id);
        // Se invoca el query se obtiene la lista resultado
        //List<CityEntity> sameID = query.getResultList();
        if (admin == null) {
            return null;
        } else {
            return admin;
        }
    }

    /**
     * devuelve todos los administradores
     * @return 
     */
    public List<AdministradorEntity> findAll() {
        LOGGER.info("Consultando todos los administradores");
        TypedQuery query = em.createQuery("select u from AdministradorEntity u", AdministradorEntity.class);
        return query.getResultList();
    }
}
