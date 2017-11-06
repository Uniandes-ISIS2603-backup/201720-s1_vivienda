/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.ejb;

import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.entities.EstudianteEntity;
import co.edu.uniandes.csw.vivienda.entities.SugerenciaEntity;
import co.edu.uniandes.csw.vivienda.persistence.CuentaPersistence;
import co.edu.uniandes.csw.vivienda.persistence.EstudiantePersistence;
import co.edu.uniandes.csw.vivienda.persistence.SugerenciaPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author e.reyesm
 */
@Stateless
public class EstudianteLogic {

    private static final Logger LOGGER = Logger.getLogger(EstudianteLogic.class.getName());

    @Inject
    private EstudiantePersistence persistence;
    

    public EstudianteEntity createEstudiante(EstudianteEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación del estudiante");
        if (persistence.find(entity.getDocumento()) != null) {
            throw new BusinessLogicException("Ya existe un estudiante con ese documento \"" + entity.getDocumento() + "\"");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación del estudiante");

        return entity;
    }

    public List<EstudianteEntity> getEstudiantes() throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todas los estudiantes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<EstudianteEntity> estudiantes = persistence.findAll();
        if (estudiantes == null) {
            estudiantes = new ArrayList<EstudianteEntity>();
            return estudiantes;
        } else {
            LOGGER.info("Termina proceso de consultar todas los estudiantes");
            return estudiantes;
        }
    }

    public EstudianteEntity getEstudiante(Long documento) throws BusinessLogicException {
        if (documento < 0) {
            throw new BusinessLogicException("El documento es invalido");
        } else {
            LOGGER.info("Inicia proceso de consulta de estudiante por documento");
            EstudianteEntity buscado = persistence.find(documento);
            if (buscado == null) {
                throw new BusinessLogicException("No existe un estudiante con ese documento");
            } else {
                return buscado;
            }
        }
    }

    public EstudianteEntity updateEstudiante(EstudianteEntity entidad) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de actualizar el estudiante");
        EstudianteEntity buscado = persistence.find(entidad.getDocumento());
        if (buscado == null) {
            throw new BusinessLogicException("No existe un estudiante con ese documento");
        } else {
            if (entidad.getDocumento() < 0) {
                throw new BusinessLogicException("El documento a actualizar no es valido");
            } else {
                return persistence.update(entidad);
            }
        }
    }

    public void deleEstudiante(Long documento) throws BusinessLogicException {
        LOGGER.info("Iniciando proceso de borrar estudiante");
        EstudianteEntity buscado = persistence.find(documento);
        if (buscado == null) {
            throw new BusinessLogicException("No existe un estudiante con ese documento");
        } else {
            List<SugerenciaEntity> lista = buscado.getSugerencias();
             LOGGER.info("Paso la lista pero como que no la lee");
            if (lista != null) {
                if (lista.isEmpty() == false) {
                    for (SugerenciaEntity cop : lista) {
                        cop.setEstudiante(null);
                        cop.setAdministrador(null);
                    }
                }
            }
            persistence.delete(documento);
        }
    }

}
