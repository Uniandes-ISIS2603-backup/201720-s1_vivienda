/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;

import co.edu.uniandes.csw.vivienda.entities.TarjetaEntity;
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
public class TarjetaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(TarjetaPersistence.class.getName());

    @PersistenceContext(unitName = "viviendaPU")
    protected EntityManager em;
    
     /**
     *
     * @param entity objeto tarjeta que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public TarjetaEntity create(TarjetaEntity entity) {
        LOGGER.info("Creando una tarjeta nueva");
        em.persist(entity);
        LOGGER.info("Creando una tarjeta nueva");
        return entity;
    }
    
        /**
     * Actualiza una tarjeta.
     *
     * @param entity: la tarjeta que viene con los nuevos cambios. 
     * @return una tarjeta con los cambios aplicados.
     */
    public TarjetaEntity update(TarjetaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando tarjeta con id={0}", entity.getNumeroTarjeta());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la bodega con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }
    
    /**
     *
     * Borra una tarjeta de la base de datos recibiendo como argumento el id
     * de la tarjeta
     *
     * @param numeroTarjeta: id correspondiente a la tarjeta a borrar.
     */
    public void delete(Long numeroTarjeta) {
        LOGGER.log(Level.INFO, "Borrando tarjeta con id={0}", numeroTarjeta);
        // Se hace uso de mismo método que esta explicado en public bodegaEntity find(Long id) para obtener la bodega a borrar.
        TarjetaEntity entity = em.find(TarjetaEntity.class, numeroTarjeta);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from bodegaEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }
    
        /**
     * Busca si hay alguna tarjeta con el numeroTarjeta que se envía de argumento
     *
     * @param numeroTarjeta: id correspondiente a la tarejta buscada.
     * @return una tarjeta.
     */
    public TarjetaEntity find(Long numeroTarjeta) {
        LOGGER.log(Level.INFO, "Consultando bodega con id={0}", numeroTarjeta);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from bodegaEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(TarjetaEntity.class, numeroTarjeta);
    }

        /**
     * Devuelve todas las tarjetas de la base de datos.
     *
     * @return una lista con todas las tarjetas que encuentre en la base de
     * datos
     */
    public List<TarjetaEntity> findAll() {
        LOGGER.info("Consultando todas las tarjetas");
        // Se crea un query para buscar todas las bodegas en la base de datos.
        TypedQuery query = em.createQuery("select u from TarjetaEntity u", TarjetaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de bodegaes.
        return query.getResultList();
    }
    
        /**
     * Busca si hay algun bodega con el nombre que se envía de argumento
     *
     * @param name: dirección de la bodega que se está buscando
     * @return null si no existe ningun bodega con la dirección del argumento.
     * Si existe alguna devuelve la primera.
     */
    public TarjetaEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando bodega por dirección ", name);

        // Se crea un query para buscar bodegas con la dirección que recibe el método como argumento. ":address" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From TarjetaEntity e where e.nombre = :name", TarjetaEntity.class);
        // Se remplaza el placeholder ":cancion" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<TarjetaEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }


}