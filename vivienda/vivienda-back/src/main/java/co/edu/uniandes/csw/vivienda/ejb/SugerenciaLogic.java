/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.ejb;

import co.edu.uniandes.csw.vivienda.entities.SugerenciaEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.SugerenciaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author e.reyesm
 */

@Stateless
public class SugerenciaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(SugerenciaLogic.class.getName());

    @Inject
    private SugerenciaPersistence persistence;

    public SugerenciaEntity createSugerencia(SugerenciaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de una sugerencia");
        if (persistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una sugerencia con ese ID \"" + entity.getId() + "\"");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de una sugerencia");

        return entity;
    }

    public List<SugerenciaEntity> getSugerencias() throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todas las sugerencias");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<SugerenciaEntity> sugerencias = persistence.findAll();
        if (sugerencias.get(0) == null) {
            throw new BusinessLogicException("La lista de sugerencias esta vacia");
        } else {
            LOGGER.info("Termina proceso de consultar todas las sugerencias");
            return sugerencias;
        }
    }

    public SugerenciaEntity getSugerencia(Long id) throws BusinessLogicException {
        if (id < 0) {
            throw new BusinessLogicException("El ID es invalido");
        } else {
            LOGGER.info("Inicia proceso de consulta de una sugerencia por ID");
            SugerenciaEntity buscado = persistence.find(id);
            if (buscado == null) {
                throw new BusinessLogicException("No existe una sugerencia con ese ID");
            } else {
                return buscado;
            }
        }
    }

    public SugerenciaEntity updateSugerencia(SugerenciaEntity entidad) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de actualizar la sugerencia");
        SugerenciaEntity buscado = persistence.find(entidad.getId());
        if (buscado == null) {
            throw new BusinessLogicException("No existe una sugerencia con ese ID");
        } else {
            if (entidad.getId() < 0) {
                throw new BusinessLogicException("El ID a actualizar no es valido");
            } else {
                return persistence.update(entidad);
            }
        }
    }

    public void deleSugerencia(Long id) throws BusinessLogicException {
        LOGGER.info("Iniciando proceso de borrar una sugerencia");
        SugerenciaEntity buscado = persistence.find(id);
        if (buscado == null) {
            throw new BusinessLogicException("No existe una sugerencia con ese ID");
        } else {
            persistence.delete(id);
        }
    }
}
