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
 * @author mp.franco10
 */
@Stateless
public class PagoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(TarjetaPersistence.class.getName());

    @PersistenceContext(unitName = "viviendaPU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto pago que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PagoEntity create(PagoEntity entity) {
        LOGGER.info("Creando un pago nueva");
        em.persist(entity);
        LOGGER.info("Creando un pago nueva");
        return entity;
    }
    
        /**
     * Actualiza un pago.
     *
     * @param entity: el pago que viene con los nuevos cambios. 
     * @return un pago con los cambios aplicados.
     */
    public PagoEntity update(PagoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando pago con número={0}", entity.getNumeroPago());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la bodega con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }
    
    /**
     *
     * Borra un pago de la base de datos recibiendo como argumento el numero
     * del pago
     *
     * @param numeroPago: id correspondiente al pago a borrar.
     */
    public void delete(Long numeroPago) {
        LOGGER.log(Level.INFO, "Borrando pago con id={0}", numeroPago);
        // Se hace uso de mismo método que esta explicado en public bodegaEntity find(Long id) para obtener la bodega a borrar.
        PagoEntity entity = em.find(PagoEntity.class, numeroPago);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from bodegaEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }
    
        /**
     * Busca si hay algun pago con el numeroPago que se envía de argumento
     *
     * @param numeroPago: id correspondiente al pago buscado.
     * @return un pago.
     */
    public PagoEntity find(Long numeroPago) {
        LOGGER.log(Level.INFO, "Consultando pago con id={0}", numeroPago);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from bodegaEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(PagoEntity.class, numeroPago);
    }

        /**
     * Devuelve todos los pagos de la base de datos.
     *
     * @return una lista con todas los pagos que encuentre en la base de
     * datos
     */
    public List<PagoEntity> findAll() {
        LOGGER.info("Consultando todos los pagos");
        // Se crea un query para buscar todas las bodegas en la base de datos.
        TypedQuery query = em.createQuery("select u from PagoEntity u", PagoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de bodegaes.
        return query.getResultList();
    }

}
