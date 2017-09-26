/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.ejb;

import co.edu.uniandes.csw.vivienda.entities.CuentaEntity;
import co.edu.uniandes.csw.vivienda.entities.TarjetaEntity;
import co.edu.uniandes.csw.vivienda.persistence.TarjetaPersistence;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author mp.franco10
 */
@Stateless
public class TarjetaLogic {
    
     private static final Logger LOGGER = Logger.getLogger(TarjetaLogic.class.getName());

    @Inject
    private TarjetaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
        /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public TarjetaEntity createTarjeta(TarjetaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de editorial");
        // Verifica la regla de negocio que dice que no puede haber dos editoriales con el mismo nombre
        if (persistence.find(entity.getNumeroTarjeta()) != null) {
            throw new BusinessLogicException("Ya existe una tarjeta con el id \"" + entity.getNumeroTarjeta() + "\"");
        }
        // Invoca la persistencia para crear la editorial

        LOGGER.info("Termina proceso de creación de editorial");
        return persistence.create(entity);
    }
    
    /**
     *
     * Obtener todas las tarjetas existentes en la base de datos.
     *
     * @return una lista de tarjetas.
     */
    public List<TarjetaEntity> getTarjetas() {
        LOGGER.info("Inicia proceso de consultar todas las tarjetas");
        List<TarjetaEntity> tarjetas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las tarjetas");
        return tarjetas;
    }
    
     /**
     *
     * Obtener una tarjeta por medio de su número tarjeta.
     *
     * @param numeroTarjeta: id de la tarjeta para ser buscada.
     * @return la tarjeta solicitada por medio de su id.
     */
    public TarjetaEntity getTarjeta(Long numeroTarjeta) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar tarjeta con id={0}", numeroTarjeta);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        TarjetaEntity tarjeta = persistence.find(numeroTarjeta);
        if (tarjeta == null) {
            LOGGER.log(Level.SEVERE, "La tarjeta con el id {0} no existe", numeroTarjeta);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar tarjeta con id={0}", numeroTarjeta);
        return tarjeta;
    }
    
        /**
     *
     * Actualizar una tarjeta.
     *
     * @param numeroTarjeta: id de la tarjeta para buscarla en la base de datos.
     * @param entity: tarjeta con los cambios para ser actualizada, por
     * ejemplo el nombre.
     * @return la tarjeta con los cambios actualizados en la base de datos.
     */
    public TarjetaEntity updateTarjeta(Long numeroTarjeta, TarjetaEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar tarjeta con id={0}", numeroTarjeta);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        TarjetaEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar tarjeta con id={0}", entity.getNumeroTarjeta());
        return newEntity;
    }

     /**
     * Borrar una tarjeta
     *
     * @param numeroTarjeta: id de la tarjeta a borrar
     */
    public void deleteTarjeta(Long numeroTarjeta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar tarjeta con id={0}", numeroTarjeta);
        if(persistence.find(numeroTarjeta)==null)
        {
        throw new BusinessLogicException("No se pudo borrar la tarjeta porque no existe.");
        }
        persistence.delete(numeroTarjeta);
        LOGGER.info("Termina proceso de eliminar una tarjeta");
    }
    
     /**
     * Retorna la cuenta asociada a una tarjeta
     *
     * @param numeroTarjeta
     * @return
     */
    public CuentaEntity getCuenta(Long numeroTarjeta) {
        return getTarjeta(numeroTarjeta).getCuenta();
    }


}
