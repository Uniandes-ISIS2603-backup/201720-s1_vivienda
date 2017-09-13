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
 * @author da.ramirezv
 */
@Stateless
public class PrestadorPersistence {
    private static final Logger LOGGER = Logger.getLogger(PrestadorPersistence.class.getName());
     @PersistenceContext(unitName = "viviendaPU")
     protected EntityManager em; 
     /**
     *
     * @param entity objeto piso que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
     public PrestadorEntity create(PrestadorEntity entity){
         LOGGER.info("Creando un apartamento nuevo");
         em.persist(entity);
         LOGGER.info("Creando un apartamento nuevo");
         return entity; 
     }
      /**
     * Actualiza un apartamento.
     *
     * @param entity: el apartamento que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un apartamento con los cambios aplicados.
     */
    public PrestadorEntity update(PrestadorEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando apartamento con id={0}", entity.getDocumento());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        el apartamento con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }
    
    /**
     *
     * Borra un apartamento de la base de datos recibiendo como argumento el id
     * del apartamento
     *
     * @param id: id correspondiente el apartamento a borrar.
     */
    public void delete(Integer id) {
        LOGGER.log(Level.INFO, "Borrando apartamento con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public apartamentoEntity find(Long id) para obtener el apartamento a borrar.
        PrestadorEntity entity = em.find(PrestadorEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from apartamentoEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }
      /**
     * Busca si hay algun apartamento con el id que se envía de argumento
     *
     * @param id: id correspondiente al apartamento buscada.
     * @return un apartamento.
     */
    public PrestadorEntity find(Integer id) {
        LOGGER.log(Level.INFO, "Consultando piso con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from pisoEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(PrestadorEntity.class, id);
    }
    
    /**
     * Devuelve todas los apartamentos de la base de datos.
     *
     * @return una lista con todas los apartamentos que encuentre en la base de
     * datos, "select u from apartamentoEntity u" es como un "select * from
     * torreEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<PrestadorEntity> findAll() {
        LOGGER.info("Consultando todas los apartamentos");
        // Se crea un query para buscar todas los Apartamentos en la base de datos.
        TypedQuery query = em.createQuery("select u from ApartamentoEntity u",PrestadorEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de apartamentos.
        return query.getResultList();
    }
}
