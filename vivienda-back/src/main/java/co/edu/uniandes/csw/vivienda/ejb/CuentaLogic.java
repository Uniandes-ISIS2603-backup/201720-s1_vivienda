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

    @Inject
    private OrdenPagoLogic ordenPagoLogic;


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
        LOGGER.log(Level.INFO, "Inicia proceso de borrar editorial con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        if (persistence.find(id) == null) {
            LOGGER.log(Level.SEVERE, "La cuenta con el id {0} no existe", id);
        }
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar editorial con id={0}", id);

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
     * Ordenes pagadas
     */
    /**
     * Crear una orden en la cuenta
     *
     * @param ordenPago de la orden a asociar
     * @param cuentaId
     * @return
     */
    public OrdenPagoEntity createOrdenPago(OrdenPagoEntity ordenPago, Long cuentaId) throws BusinessLogicException {
        CuentaEntity cuentaEntity = getCuenta(cuentaId);
        OrdenPagoEntity ordenPagoEntity = ordenPagoLogic.createOrdenPago(ordenPago);
        if (ordenPagoEntity.isPagada()) {
            List<OrdenPagoEntity> lista = cuentaEntity.getOrdenPagosPaid();
            lista.add(ordenPago);
            cuentaEntity.setOrdenPagosPaid(lista);
            LOGGER.log(Level.INFO, "Se creó una cuenta pagada");
        } else if (!ordenPagoEntity.isPagada()) {
            List<OrdenPagoEntity> lista = cuentaEntity.getOrdenPagosNotPaid();
            lista.add(ordenPago);
            cuentaEntity.setOrdenPagosNotPaid(lista);
            LOGGER.log(Level.INFO, "Se creó una cuenta no pagada");
        }
        return ordenPagoEntity;
    }

    /**
     * Borrar una orden de pago de una cuenta
     *
     * @param ordenPagoid
     * @param cuentaId
     */
    public void deleteOrdenPago(Long ordenPagoid, Long cuentaId) throws BusinessLogicException {
        CuentaEntity cuentaEntity = getCuenta(cuentaId);
        OrdenPagoEntity orden = ordenPagoLogic.getOrdenPago(ordenPagoid);
        ordenPagoLogic.deleteOrdenPago(ordenPagoid);
        if (!orden.isPagada()) {
            List<OrdenPagoEntity> lista = cuentaEntity.getOrdenPagosNotPaid();
            lista.remove(orden);
            cuentaEntity.setOrdenPagosNotPaid(lista);

        } else if (orden.isPagada()) {
            List<OrdenPagoEntity> lista = cuentaEntity.getOrdenPagosPaid();
            lista.remove(orden);
            cuentaEntity.setOrdenPagosPaid(lista);

        }
    }

    /**
     * Cambia la información de una orden de pago
     *
     * @param entityNueva
     * @param cuentaId
     * @return
     */
    public OrdenPagoEntity updateOrdenPago(Long cuentaId, OrdenPagoEntity entityNueva) throws BusinessLogicException {
        CuentaEntity cuentaEntity = getCuenta(cuentaId);
        OrdenPagoEntity entityVieja = ordenPagoLogic.getOrdenPago(entityNueva.getIdPago());
        //Si difieren entonces la orden se cambia de lista
        OrdenPagoEntity entityNew = ordenPagoLogic.updateOrdenPago(entityNueva);
        if (entityNueva.isPagada() && !entityVieja.isPagada()) {
            //Intercambio de listas
            List<OrdenPagoEntity> listaNot = cuentaEntity.getOrdenPagosNotPaid();
            listaNot.remove(entityVieja);
            cuentaEntity.setOrdenPagosNotPaid(listaNot);
            List<OrdenPagoEntity> listaYes = cuentaEntity.getOrdenPagosPaid();
            listaYes.add(entityNueva);
            cuentaEntity.setOrdenPagosPaid(listaYes);

        } else if (!entityNueva.isPagada() && entityVieja.isPagada()) {
            //Intercambio de listas
            List<OrdenPagoEntity> listaNot = cuentaEntity.getOrdenPagosNotPaid();
            listaNot.add(entityNueva);
            cuentaEntity.setOrdenPagosNotPaid(listaNot);
            List<OrdenPagoEntity> listaYes = cuentaEntity.getOrdenPagosPaid();
            listaYes.remove(entityVieja);
            cuentaEntity.setOrdenPagosPaid(listaYes);

        }
        return entityNew;
    }

    /**
     * Retorna todas las ordenes de pago pagadas asociadas a una cuenta
     *
     * @param cuentaId
     * @return
     */
    public List<OrdenPagoEntity> getOrdenPagosPaid(Long cuentaId) {
        return getCuenta(cuentaId).getOrdenPagosPaid();
    }

    /**
     * Retorna todas las ordenes de pago no pagadas asociadas a una cuenta
     *
     * @param cuentaId
     * @return
     */
    public List<OrdenPagoEntity> getOrdenPagosNotPaid(Long cuentaId) {
        return getCuenta(cuentaId).getOrdenPagosNotPaid();
    }

    /**
     * Retorna una orden de pago asociada a una cuenta
     *
     * @param cuentaId
     * @param ordenId
     * @return
     * @throws BusinessLogicException
     */
    public OrdenPagoEntity getOrdenPago(Long cuentaId, Long ordenId) throws BusinessLogicException {
        CuentaEntity cuenta = getCuenta(cuentaId);
        OrdenPagoEntity orden = ordenPagoLogic.getOrdenPago(ordenId);
        //Para evitar problemas de búsqueda se revisa si no está en las dos listas
        if (!cuenta.getOrdenPagosPaid().contains(orden) || !cuenta.getOrdenPagosNotPaid().contains(orden)) {
            throw new BusinessLogicException("La orden de pago no está asociada a ninguna lista");
        }
        return orden;
    }

    /**
     * Obtiene una colección de instancias de OrdenPagoEntity asociadas a una
     * instancia de cuenta
     *
     * @param cuentaId Identificador de la instancia de cuenta
     * @return Colección de instancias de OrdenPagoEntity asociadas a la
     * instancia de cuenta
     *
     */
    public List<OrdenPagoEntity> listOrdenPagosNotPaid(Long cuentaId) {
        return getCuenta(cuentaId).getOrdenPagosNotPaid();

    }

    /**
     * Obtiene una colección de instancias de OrdenPagoEntity asociadas a una
     * instancia de cuenta
     *
     * @param cuentaId Identificador de la instancia de cuenta
     * @return Colección de instancias de OrdenPagoEntity asociadas a la
     * instancia de cuenta
     *
     */
    public List<OrdenPagoEntity> listOrdenPagosPaid(Long cuentaId) {
        return getCuenta(cuentaId).getOrdenPagosPaid();

    }
}
