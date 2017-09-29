/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.ejb;

import co.edu.uniandes.csw.vivienda.entities.CuentaEntity;
import co.edu.uniandes.csw.vivienda.entities.EstudianteEntity;
import co.edu.uniandes.csw.vivienda.entities.OrdenPagoEntity;
import co.edu.uniandes.csw.vivienda.entities.TarjetaEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.CuentaPersistence;
import java.util.ArrayList;
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
public class CuentaLogic {

    private static final Logger LOGGER = Logger.getLogger(CuentaLogic.class.getName());

    @Inject
    private CuentaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.



    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public CuentaEntity createCuenta(CuentaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de cuenta");
        // Verifica la regla de negocio que dice que no puede haber dos editoriales con el mismo nombre
        if (persistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una Cuenta con el nombre \"" + entity.getId() + "\"");
        }
        LOGGER.info("Termina proceso de creación de cuenta");
        return persistence.create(entity);
    }

    /**
     *
     * Obtener todas las cuentas existentes en la base de datos.
     *
     * @return una lista de cuentas.
     */
    public List<CuentaEntity> getCuentas() {
        LOGGER.info("Inicia proceso de consultar todas las cuentas");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<CuentaEntity> cuentas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las editoriales");
        return cuentas;
    }

    /**
     *
     * Obtener una cuenta por medio de su id.
     *
     * @param id: id de la cuenta para ser buscada.
     * @return la cuenta solicitada por medio de su id.
     */
    public CuentaEntity getCuenta(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar cuenta con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        CuentaEntity cuenta = persistence.find(id);
        if (cuenta == null) {
            LOGGER.log(Level.SEVERE, "La cuenta con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar cuenta con id={0}", id);
        return cuenta;
    }

    /**
     *
     * Actualizar una cuenta.
     *
     * @param id: id de la cuenta para buscarla en la base de datos.
     * @param entity: cuenta con los cambios para ser actualizada.
     * @return la cuenta con los cambios actualizados en la base de datos.
     */
    public CuentaEntity updateCuenta(Long id, CuentaEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar cuenta con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        CuentaEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar cuenta con id={0}", entity.getId());
        return newEntity;
    }

    /**
     * Borrar un cuenta
     *
     * @param id: id de la cuenta a borrar
     */
    public void deleteCuenta(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar cuenta con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        if (persistence.find(id) == null) {
            LOGGER.log(Level.SEVERE, "La cuenta con el id {0} no existe", id);
        }
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar cuenta con id={0}", id);

    }

    /**
     * Retorna el estudiante asociado a una editorial
     *
     * @param id
     * @return
     */
    public EstudianteEntity getEstudiante(Long id) {
        return getCuenta(id).getEstudiante();
    }

    /**
     * Retorna todas las tarjetas asociadas a una editorial
     *
     * @param id
     * @return
     */
    public List<TarjetaEntity> getTarjetas(Long id) {
        return getCuenta(id).getTarjeta();
    }
    
    /**
     * Retorna todas las ordenes de pago pagadas asociadas a una cuenta
     *
     * @param cuentaId
     * @return
     */
    public List<OrdenPagoEntity> getOrdenPagosCuenta(Long cuentaId) {
        return getCuenta(cuentaId).getOrdenPagos();
    }
   
}
