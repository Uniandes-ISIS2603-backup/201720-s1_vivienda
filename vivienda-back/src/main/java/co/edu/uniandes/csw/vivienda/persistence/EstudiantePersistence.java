/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;

import co.edu.uniandes.csw.vivienda.entities.EstudianteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author e.reyesm
 */
@Stateless
public class EstudiantePersistence 
{
     private static final Logger LOGGER = Logger.getLogger(EstudiantePersistence.class.getName());

    @PersistenceContext(unitName ="viviendaPU")
    protected EntityManager em;
    
    public EstudianteEntity create(EstudianteEntity entity) {
        LOGGER.info("Creando un estudiante nuevo");
        em.persist(entity);
        return entity;
    }

    public EstudianteEntity update(EstudianteEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando un estudiante con documento={0}", entity.getDocumento());
        return em.merge(entity);
    }

    public void delete(Long documento) {
        LOGGER.log(Level.INFO, "Borrando estudiante con documento={0}", documento);
        EstudianteEntity borrar = find(documento);
        em.remove(borrar);
    }

    public EstudianteEntity find(Long documento) {
        LOGGER.log(Level.INFO, "Consultando estudiante con documento={0}", documento);
        return em.find(EstudianteEntity.class, documento);
        
    }

    public List<EstudianteEntity> findAll() {
        LOGGER.info("Consultando todas los estudiantes");
         TypedQuery query = em.createQuery("select u from EstudianteEntity u", EstudianteEntity.class);
         return query.getResultList();
    }
    
}
