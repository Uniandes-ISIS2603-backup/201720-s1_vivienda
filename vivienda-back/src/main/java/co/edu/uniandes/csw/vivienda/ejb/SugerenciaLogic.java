/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.ejb;

import co.edu.uniandes.csw.vivienda.entities.AdministradorEntity;
import co.edu.uniandes.csw.vivienda.entities.EstudianteEntity;
import co.edu.uniandes.csw.vivienda.entities.SugerenciaEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.AdministradorPersistence;
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
public class SugerenciaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(SugerenciaLogic.class.getName());
    
    @Inject
    private SugerenciaPersistence persistence;
    @Inject
    private AdministradorPersistence adminPersistence;
    @Inject
    private EstudiantePersistence estudPersistence;
    
    /**
     * Se encarga de crear una Sugerencia en la base de datos.
     * @param entity Objeto de SugerenciaEntity con los datos nuevos.
     * @return Objeto de SugerenciaEntity con los datos nuevos.
     * @generated
     */
    
    public SugerenciaEntity createSugerencia(SugerenciaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de una sugerencia");
        if (persistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una sugerencia con ese ID \"" + entity.getId() + "\"");
        } else if (estudPersistence.find(entity.getEstudiante().getDocumento()) == null) {
            throw new BusinessLogicException("No existe el estudiante con documento \"" + entity.getEstudiante().getDocumento() + "\"");
        } else if (adminPersistence.findByID(entity.getAdministrador().getDocumento()) == null) {
            throw new BusinessLogicException("No existe el administrador con documento \"" + entity.getAdministrador().getDocumento() + "\"");
        } else {
            
            AdministradorEntity nuevo = adminPersistence.findByID(entity.getAdministrador().getDocumento());
            EstudianteEntity nuevo2 = estudPersistence.find(entity.getEstudiante().getDocumento());
            
            List<SugerenciaEntity> antigua = nuevo.getSugerencias();
            List<SugerenciaEntity> antigua2 = nuevo2.getSugerencias();
            
            List<SugerenciaEntity> copiar = new ArrayList<SugerenciaEntity>();
            List<SugerenciaEntity> copiar2 = new ArrayList<SugerenciaEntity>();
            if (antigua != null) {
                for (SugerenciaEntity cop : antigua) {
                    copiar.add(cop);
                }
            }
            if (antigua2 != null) {
                for (SugerenciaEntity cop : antigua2) {
                    copiar2.add(cop);
                }
            }
            copiar.add(entity);
            copiar2.add(entity);
            nuevo.setSugerencias(copiar);
            nuevo2.setSugerencias(copiar2);
            
            persistence.create(entity);
            LOGGER.info("Termina proceso de creación de una sugerencia");
            
            return entity;
        }
    }
    
     /**
     * Obtiene la lista de los registros de Sugerencia.
     * @return Colección de objetos de SugerenciaEntity.
     * @generated
     */
    
    public List<SugerenciaEntity> getSugerencias() throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todas las sugerencias");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<SugerenciaEntity> sugerencias = persistence.findAll();
        if (sugerencias == null) {
            sugerencias = new ArrayList<>();
            return sugerencias;
        } else {
            LOGGER.info("Termina proceso de consultar todas las sugerencias");
            return sugerencias;
        }
    }
    
    /**
     * Obtiene los datos de una instancia de Sugerencia a partir de su ID.
     * @param id Identificador de la instancia a consultar.
     * @return Instancia de SugerenciaEntity con los datos del la Sugerencia consultada.
     * @generated
     */
    
    public SugerenciaEntity getSugerencia(Long id) throws BusinessLogicException {
        if (id <= 0) {
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
    
     /**
     * Actualiza la información de una instancia de Sugerencia.
     * @param entidad Instancia de SugerenciaEntity con los nuevos datos.
     * @return Instancia de SugerenciaEntity con los datos actualizados.
     * @generated
     */
    
    public SugerenciaEntity updateSugerencia(SugerenciaEntity entidad) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de actualizar la sugerencia");
        SugerenciaEntity buscado = persistence.find(entidad.getId());
        if (buscado == null) {
            throw new BusinessLogicException("No existe una sugerencia con ese ID");
        }        
        if (buscado.getAdministrador() == null) {
            throw new BusinessLogicException("No existe una sugerencia con ese ID");
            
        }
        if (buscado.getEstudiante() == null) {
            throw new BusinessLogicException("No existe una sugerencia con ese ID");
            
        } else {
            
            AdministradorEntity nuevo = adminPersistence.findByID(entidad.getAdministrador().getDocumento());
            EstudianteEntity nuevo2 = estudPersistence.find(entidad.getEstudiante().getDocumento());
            List<SugerenciaEntity> antigua = nuevo.getSugerencias();
            List<SugerenciaEntity> antigua2 = nuevo2.getSugerencias();
            List<SugerenciaEntity> copiar = new ArrayList<SugerenciaEntity>();
            List<SugerenciaEntity> copiar2 = new ArrayList<SugerenciaEntity>();
            if (antigua != null) {
                for (SugerenciaEntity cop : antigua) {
                    if (cop.getId() != entidad.getId()) {
                        copiar.add(cop);
                    }
                }
            }
            if (antigua2 != null) {
                for (SugerenciaEntity cop : antigua2) {
                    if (cop.getId() != entidad.getId()) {
                        copiar2.add(cop);
                    }
                }
            }
            copiar.add(entidad);
            copiar2.add(entidad);
            nuevo.setSugerencias(copiar);
            nuevo2.setSugerencias(copiar2);
          
                
            return persistence.update(entidad);
        }
        
    }
    
     /**
     * Elimina una instancia de Sugerencia de la base de datos.
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    
    public void deleSugerencia(Long id) throws BusinessLogicException {
        
        LOGGER.info("Iniciando proceso de borrar una sugerencia");
        SugerenciaEntity buscado = persistence.find(id);
        if (buscado == null) {
            throw new BusinessLogicException("No existe una sugerencia con ese ID");
        }        
        if (buscado.getAdministrador() == null) {
            persistence.delete(id);
        }
        else if (buscado.getEstudiante() == null) {
            persistence.delete(id);
        } else {
            
            AdministradorEntity nuevo = adminPersistence.findByID(buscado.getAdministrador().getDocumento());
            EstudianteEntity nuevo2 = estudPersistence.find(buscado.getEstudiante().getDocumento());
            List<SugerenciaEntity> antigua = nuevo.getSugerencias();
            List<SugerenciaEntity> antigua2 = nuevo2.getSugerencias();
            List<SugerenciaEntity> copiar = new ArrayList<SugerenciaEntity>();
            List<SugerenciaEntity> copiar2 = new ArrayList<SugerenciaEntity>();
            if (antigua != null) {
                for (SugerenciaEntity cop : antigua) {
                    if (cop.getId() != buscado.getId()) {
                        copiar.add(cop);
                    }
                }
            }
            
            if (antigua2 != null) {
                for (SugerenciaEntity cop : antigua2) {
                    if (cop.getId() != buscado.getId()) {
                        copiar2.add(cop);
                    }
                }
            }
            
            if (copiar.isEmpty() == false) {
                nuevo.setSugerencias(copiar);
            } else {
                nuevo.setSugerencias(null);
            }
            if (copiar2.isEmpty() == false) {
                nuevo2.setSugerencias(copiar2);
            } else {
                nuevo2.setSugerencias(null);
            }
            
            persistence.delete(id);
        }
    }
}
