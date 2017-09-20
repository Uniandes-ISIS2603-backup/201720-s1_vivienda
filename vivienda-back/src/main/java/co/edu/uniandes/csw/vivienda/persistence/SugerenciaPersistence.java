/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;

import co.edu.uniandes.csw.vivienda.entities.SugerenciaEntity;
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
public class SugerenciaPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(EstudiantePersistence.class.getName());

    @PersistenceContext(unitName ="viviendaPU")
    protected EntityManager em;
    
    public SugerenciaEntity create(SugerenciaEntity  entity) {
        LOGGER.info("Creando una sugerencia nueva");
        em.persist(entity);
        return entity;
    }

    public SugerenciaEntity  update(SugerenciaEntity  entity) {
        LOGGER.log(Level.INFO, "Actualizando una sugerencia con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando sugerencia con id={0}", id);
        em.remove(em.find(SugerenciaEntity.class, id));
    }

    public SugerenciaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando sugerencia con id={0}", id);
        return em.find(SugerenciaEntity .class, id);
    }

    public List<SugerenciaEntity > findAll() {
        LOGGER.info("Consultando todas las sugerencias");
        return em.createQuery("select u from SugerenciaEntity u", SugerenciaEntity .class).getResultList();
    }
}
