/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;


import co.edu.uniandes.csw.vivienda.entities.ServicioEntity;
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
public class ServicioPersistence {
     
    private static final Logger LOGGER = Logger.getLogger(co.edu.uniandes.csw.vivienda.persistence.ServicioPersistence.class.getName());

    @PersistenceContext(unitName = "viviendaPU")
    protected EntityManager em;
     /**
     *
     * @param entity objeto city que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ServicioEntity create(ServicioEntity entity) {
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
    public ServicioEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando prestador por nombre", nombre);

        // Se crea un query para buscar cityes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ServicioEntity e where e.nombre = :nombre", ServicioEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<ServicioEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
    
    //mio
     public ServicioEntity update(ServicioEntity entity) {
        
        // Se crea un query para buscar cityes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        ServicioEntity prestadorreferencia = em.merge(entity);
        // Se remplaza el placeholder ":name" con el valor del argumento
       
        return prestadorreferencia;
        
    }
     
     public void delete(String nombre) {
        ServicioEntity temp = findByName(nombre);
        em.remove(temp);
     }
       
     
    
  

    public List<ServicioEntity> findAll() {
        LOGGER.info("Consultando todos los administradores");
        TypedQuery query = em.createQuery("select u from ServicioEntity u", ServicioEntity.class);
        return query.getResultList();
    }

   
    
}
