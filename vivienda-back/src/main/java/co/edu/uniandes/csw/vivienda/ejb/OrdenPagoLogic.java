/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.ejb;

import co.edu.uniandes.csw.vivienda.entities.OrdenPagoEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.OrdenPagoPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author e.reyesm
 */
@Stateless
public class OrdenPagoLogic {
    private static final Logger LOGGER = Logger.getLogger(OrdenPagoLogic.class.getName());

    @Inject
    private OrdenPagoPersistence persistence;

    public OrdenPagoEntity createOrdenPago(OrdenPagoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de una orden de pago");
        if(persistence.find(entity.getIdPago())!=null)
        { 
            throw new BusinessLogicException("Ya existe la orden de pago");
        }
        LOGGER.info("Si se está creando en la persistencia");
        LOGGER.info("Termina proceso de creación de una orden de pago");
        return persistence.create(entity);
        
    }

    public List<OrdenPagoEntity> getOrdenesPagos() throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todas las ordenes de pagos");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<OrdenPagoEntity> ordenesPagos = persistence.findAll();
        if (ordenesPagos.get(0) == null) {
            throw new BusinessLogicException("La lista de ordenes de pagos esta vacia");
        } else {
            LOGGER.info("Termina proceso de consultar todas las ordenes de pagos");
            return ordenesPagos;
        }
    }

    public OrdenPagoEntity getOrdenPago(Long id) throws BusinessLogicException {
 
            LOGGER.info("Inicia proceso de consulta de una orden de pago por ID");
            OrdenPagoEntity buscado = persistence.find(id);
            if (buscado == null) {
                throw new BusinessLogicException("No existe una orden de pago con ese ID");
            } else {
                return buscado;
            }
        
    }

    public OrdenPagoEntity updateOrdenPago(OrdenPagoEntity entidad) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de actualizar la orden de pago");
        OrdenPagoEntity buscado = persistence.find(entidad.getIdPago());
        if (buscado == null) {
            throw new BusinessLogicException("No existe una orden de pago con ese ID");
        } else {
            if (entidad.getIdPago() < 0) {
                throw new BusinessLogicException("El ID a actualizar no es valido");
            } else {
                return persistence.update(entidad);
            }
        }
    }

    public void deleteOrdenPago(Long id) throws BusinessLogicException {
        LOGGER.info("Iniciando proceso de borrar orden de pago");
        OrdenPagoEntity buscado = persistence.find(id);
        if (buscado == null) {
            throw new BusinessLogicException("No existe una orden de pago con ese ID");
        } else {
            persistence.delete(id);
        }
    }
}
