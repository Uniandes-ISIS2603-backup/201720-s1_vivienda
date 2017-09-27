/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;

import co.edu.uniandes.csw.vivienda.entities.OrdenPagoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author e.reyesm
 */
@Stateless
public class OrdenPagoPersistence 
{
         private static final Logger LOGGER = Logger.getLogger(OrdenPagoPersistence.class.getName());

    @PersistenceContext(unitName ="viviendaPU")
    protected EntityManager em;
    
    public OrdenPagoEntity create(OrdenPagoEntity entity) {
        LOGGER.info("Creando una orden de pago nueva");
        em.persist(entity);
        return entity;
    }

    public OrdenPagoEntity update(OrdenPagoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando una orden de pago con idPago={0}", entity.getIdPago());
        return em.merge(entity);
    }

    public void delete(Long idPago) {
        LOGGER.log(Level.INFO, "Borrando orden de pago con idPago={0}", idPago);
        em.remove(em.find(OrdenPagoEntity.class, idPago));
    }

    public OrdenPagoEntity find(Long idPago) {
        LOGGER.log(Level.INFO, "Consultando orden de pago con idPago={0}", idPago);
        return em.find(OrdenPagoEntity.class, idPago);
    }

    public List<OrdenPagoEntity> findAll() {
        LOGGER.info("Consultando todas las ordenes de pago");
        return em.createQuery("select u from OrdenPagoEntity u", OrdenPagoEntity.class).getResultList();
    }
    
}
