/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;


import co.edu.uniandes.csw.vivienda.entities.PrestadorEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author rj.gonzalez10
 */
@Stateless
public class PrestadorPersistence {
    
    
    private static final Logger LOGGER = Logger.getLogger(PrestadorPersistence.class.getName());

    @PersistenceContext(unitName = "viviendaPU")
    protected EntityManager em;
     /**
     *
     * @param entity objeto city que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PrestadorEntity create(PrestadorEntity entity) {
        LOGGER.info("Creando un administrador nuevo");
        em.persist(entity);
        LOGGER.info("Creando un administrador nuevo");
        return entity;
    }
    /**
     * Busca si hay alguna city con el nombre que se envía de argumento
     *
     * @param name: Nombre de la city que se está buscando
     * @return null si no existe ninguna city con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public PrestadorEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando prestador por nombre", nombre);

        // Se crea un query para buscar cityes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From PrestadorEntity e where e.nombre = :nombre", PrestadorEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<PrestadorEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
    
    //mio
     public PrestadorEntity update(PrestadorEntity entity) {
        
        // Se crea un query para buscar cityes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        PrestadorEntity prestadorreferencia = em.merge(entity);
        // Se remplaza el placeholder ":name" con el valor del argumento
       
        return prestadorreferencia;
        
    }
     
     public void delete(Long id) {
        PrestadorEntity temp = findByID(id);
        em.remove(temp);
     }
       
     
    
    //mio
    public PrestadorEntity findByID(Long id) {
        LOGGER.log(Level.INFO, "Consultando administrador por id", id);
        // Se crea un query para buscar cityes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        //TypedQuery query = em.createQuery("Select e From CityEntity e where e.id = :id", CityEntity.class);
        PrestadorEntity admin = em.find(PrestadorEntity.class, id);
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

    public List<PrestadorEntity> findAll() {
        LOGGER.info("Consultando todos los administradores");
        TypedQuery query = em.createQuery("select u from PrestadorEntity u", PrestadorEntity.class);
        return query.getResultList();
    }

   
}
