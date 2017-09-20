/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.ejb;

import co.edu.uniandes.csw.vivienda.entities.CuentaEntity;
import co.edu.uniandes.csw.vivienda.entities.OrdenPagoEntity;
import co.edu.uniandes.csw.vivienda.entities.PagoEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.PagoPersistence;
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
public class PagoLogic {

    private static final Logger LOGGER = Logger.getLogger(PagoLogic.class.getName());

    @Inject
    private PagoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private OrdenPagoLogic bookLogic;
    @Inject
    private CuentaLogic cuentaLogic;

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public PagoEntity createPago(PagoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de pago");
        if (persistence.find(entity.getNumeroPago()) != null) {
            throw new BusinessLogicException("Ya existe un pago con el número \"" + entity.getNumeroPago() + "\"");
        }
        // Invoca la persistencia para crear el pago

        LOGGER.info("Termina proceso de creación de pago");
        return persistence.create(entity);
    }

    /**
     *
     * Obtener todos los pagos existentes en la base de datos.
     *
     * @return una lista de pagos.
     */
    public List<PagoEntity> getPagos() {
        LOGGER.info("Inicia proceso de consultar todos los pagos");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<PagoEntity> editorials = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los pagos");
        return editorials;
    }

    /**
     *
     * Obtener un pago por medio de su número.
     *
     * @param numeroPago: id del pago para ser buscado.
     * @return el pago solicitado por medio de su id.
     */
    public PagoEntity getPago(Long numeroPago) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar pago con id={0}", numeroPago);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        PagoEntity pago = persistence.find(numeroPago);
        if (pago == null) {
            LOGGER.log(Level.SEVERE, "El pago con el id {0} no existe", numeroPago);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar pago con id={0}", numeroPago);
        return pago;
    }

    /**
     *
     * Actualizar un pago.
     *
     * @param numeroPago: id del pago para buscarlo en la base de datos.
     * @param entity: pago con los cambios para ser actualizado.
     * @return el pago con los cambios actualizados en la base de datos.
     */
    public PagoEntity updatePago(Long numeroPago, PagoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar pago con id={0}", numeroPago);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        PagoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar pago con id={0}", entity.getNumeroPago());
        return newEntity;
    }

    /**
     * Borrar un pago
     *
     * @param numeroPago: id del pago a borrar
     */
    public void deletePago(Long numeroPago) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar pago con id={0}", numeroPago);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        if (persistence.find(numeroPago) == null) {
            throw new BusinessLogicException("No se puede borrar el pago porque no existe");
        }
        persistence.delete(numeroPago);
        LOGGER.log(Level.INFO, "Termina proceso de borrar pago con id={0}", numeroPago);

    }
    
     /**
     * Retorna la cuenta asociada a una pago
     *
     * @param numeroPago
     * @return
     */
    public CuentaEntity getCuenta(Long numeroPago) {
        return getPago(numeroPago).getCuenta();
    }
    
    public List<OrdenPagoEntity> getServicios(Long numeroPago){
        return getPago(numeroPago).getOrdenPagos();
    
    }
}
