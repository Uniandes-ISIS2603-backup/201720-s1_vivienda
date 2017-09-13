/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;

import co.edu.uniandes.csw.vivienda.entities.PagoEntity;
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
public class PagoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(PagoPersistence.class.getName());
     @PersistenceContext(unitName = "viviendaPU")
     protected EntityManager em; 
     /**
     *
     * @param entity objeto piso que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
     public PagoEntity create(PagoEntity entity){
         LOGGER.info("Creando un piso nuevo");
         em.persist(entity);
         LOGGER.info("Creando una piso nuevo");
         return entity; 
     }
      /**
     * Actualiza un piso.
     *
     * @param entity: el piso que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un piso con los cambios aplicados.
     */
    public PagoEntity update(PagoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando piso con id={0}", entity.getDocumento());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        el piso con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }
    
    /**
     *
     * Borra un piso de la base de datos recibiendo como argumento el id
     * de el piso
     *
     * @param id: id correspondiente el piso a borrar.
     */
    public void delete(Integer id) {
        LOGGER.log(Level.INFO, "Borrando piso con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public torreEntity find(Long id) para obtener el piso a borrar.
        PagoEntity entity = em.find(PagoEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from pisoEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }
      /**
     * Busca si hay algun piso con el id que se envía de argumento
     *
     * @param id: id correspondiente al piso buscada.
     * @return un piso.
     */
    public PagoEntity find(Integer id) {
        LOGGER.log(Level.INFO, "Consultando piso con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from pisoEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(PagoEntity.class, id);
    }
    
    /**
     * Devuelve todas los pisos de la base de datos.
     *
     * @return una lista con todas los pisos que encuentre en la base de
     * datos, "select u from pisoEntity u" es como un "select * from
     * torreEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<PagoEntity> findAll() {
        LOGGER.info("Consultando todas los pisos");
        // Se crea un query para buscar todas las Torres en la base de datos.
        TypedQuery query = em.createQuery("select u from PisoEntity u",PagoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de pisos.
        return query.getResultList();
    }
    
}
