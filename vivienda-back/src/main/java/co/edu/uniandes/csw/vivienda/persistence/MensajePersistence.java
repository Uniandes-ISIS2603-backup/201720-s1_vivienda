/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;

import co.edu.uniandes.csw.vivienda.entities.MensajeEntity;
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
public class MensajePersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MensajePersistence.class.getName());

    @PersistenceContext(unitName = "viviendaPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto mensaje que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public MensajeEntity create(MensajeEntity entity) {
        LOGGER.info("Creando un mensaje nuevo");
        em.persist(entity);
        LOGGER.info("Creando un mensaje nuevo");
        return entity;
    }

    /**
     * Busca si hay alguna mensaje con el nombre que se envía de argumento
     *
     * @param name: Nombre del mensaje que se está buscando
     * @return null si no existe ningun mensaje con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public MensajeEntity findByName(String titulo) {
        LOGGER.log(Level.INFO, "Consultando mensaje por titulo", titulo);

        // Se crea un query para buscar cityes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From MensajeEntity e where e.titulo = :titulo", MensajeEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento
        query = query.setParameter("titulo", titulo);
        // Se invoca el query se obtiene la lista resultado
        List<MensajeEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
    
    /**
     * hace un update del mensaje
     * @param entity
     * @return 
     */
     public MensajeEntity update(MensajeEntity entity) {
        
        // Se crea un query para buscar cityes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        MensajeEntity mensajereferencia = em.merge(entity);
        // Se remplaza el placeholder ":name" con el valor del argumento
       
        return mensajereferencia;
        
    }
    
     /**
      * borra el mensaje
      * @param id 
      */
     public void delete(Long id) {
        MensajeEntity temp = findByID(id);
        em.remove(temp);
     }
       
     
    
    /**
     * busca el mensaje por id
     * @param id
     * @return 
     */
    public MensajeEntity findByID(Long id) {
        LOGGER.log(Level.INFO, "Consultando mensaje por id", id);
        // Se crea un query para buscar cityes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        //TypedQuery query = em.createQuery("Select e From CityEntity e where e.id = :id", CityEntity.class);
        MensajeEntity mensaje = em.find(MensajeEntity.class, id);
        // Se remplaza el placeholder ":name" con el valor del argumento
        //query = query.setParameter("id", id);
        // Se invoca el query se obtiene la lista resultado
        //List<CityEntity> sameID = query.getResultList();
        if (mensaje == null) {
            return null;
        } else {
            return mensaje;
        }
    }

    /**
     * devuelve todos los mensajes
     * @return 
     */
    public List<MensajeEntity> findAll() {
        LOGGER.info("Consultando todos los mensajes");
        TypedQuery query = em.createQuery("select u from MensajeEntity u", MensajeEntity.class);
        return query.getResultList();
    }
}
