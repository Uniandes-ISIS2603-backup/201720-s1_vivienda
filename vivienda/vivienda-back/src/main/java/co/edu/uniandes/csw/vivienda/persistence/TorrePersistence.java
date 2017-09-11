/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;

import co.edu.uniandes.csw.vivienda.entities.TorreEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author da.solano1
 */
@Stateless
public class TorrePersistence {
     private static final Logger LOGGER = Logger.getLogger(TorrePersistence.class.getName());
     @PersistenceContext(unitName = "viviendaPU")
     protected EntityManager em; 
     /**
     *
     * @param entity objeto torre que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
     public TorreEntity create(TorreEntity entity){
         LOGGER.info("Creando una Torre nueva");
         em.persist(entity);
         LOGGER.info("Creando una Torre nueva");
         return entity; 
     }
      /**
     * Actualiza una torre.
     *
     * @param entity: la torre que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un bodega con los cambios aplicados.
     */
    public TorreEntity update(TorreEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando torre con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la torre con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }
    
    /**
     *
     * Borra un torre de la base de datos recibiendo como argumento el id
     * de la torre
     *
     * @param id: id correspondiente a la torre a borrar.
     */
    public void delete(Integer id) {
        LOGGER.log(Level.INFO, "Borrando torre con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public torreEntity find(Long id) para obtener la torre a borrar.
        TorreEntity entity = em.find(TorreEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from bodegaEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }
      /**
     * Busca si hay algun torre con el id que se envía de argumento
     *
     * @param id: id correspondiente a la torre buscada.
     * @return un torre.
     */
    public TorreEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando torre con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from torreEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(TorreEntity.class, id);
    }
    
    /**
     * Devuelve todas las torres de la base de datos.
     *
     * @return una lista con todas las torres que encuentre en la base de
     * datos, "select u from bodegaEntity u" es como un "select * from
     * torreEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<TorreEntity> findAll() {
        LOGGER.info("Consultando todas las torres");
        // Se crea un query para buscar todas las Torres en la base de datos.
        TypedQuery query = em.createQuery("select u from TorreEntity u", TorreEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de torres.
        return query.getResultList();
    }
   
}
