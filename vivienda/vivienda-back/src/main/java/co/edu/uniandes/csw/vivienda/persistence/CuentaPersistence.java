/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;

import co.edu.uniandes.csw.vivienda.entities.CuentaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/**
 *
 * @author mp.franco10
 */
@Stateless
public class CuentaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(CuentaPersistence.class.getName());

    @PersistenceContext(unitName = "viviendaPU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto cuenta que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CuentaEntity create(CuentaEntity entity) {
        LOGGER.info("Creando una cuenta nueva");
        em.persist(entity);
        LOGGER.info("Creando una cuenta nueva");
        return entity;
    }
    
        /**
     * Actualiza una cuenta.
     *
     * @param entity: el pago que viene con los nuevos cambios. 
     * @return un pago con los cambios aplicados.
     */
    public CuentaEntity update(CuentaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando pago con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la bodega con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }
    
    /**
     *
     * Borra una cuenta de la base de datos recibiendo como argumento el id
     *
     * @param id: id correspondiente al pago a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando cuenta con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public bodegaEntity find(Long id) para obtener la bodega a borrar.
        CuentaEntity entity = em.find(CuentaEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from bodegaEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }
    
        /**
     * Busca si hay algun pago con el numeroPago que se envía de argumento
     *
     * @param id: id correspondiente a la cuenta buscado.
     * @return un pago.
     */
    public CuentaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando cuenta con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from bodegaEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(CuentaEntity.class, id);
    }

        /**
     * Devuelve todos las cuentas de la base de datos.
     *
     * @return una lista con todas las cuentas que encuentre en la base de
     * datos
     */
    public List<CuentaEntity> findAll() {
        LOGGER.info("Consultando todas las cuentas");
        // Se crea un query para buscar todas las bodegas en la base de datos.
        TypedQuery query = em.createQuery("select u from CuentaEntity u", CuentaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de bodegaes.
        return query.getResultList();
    }
}
