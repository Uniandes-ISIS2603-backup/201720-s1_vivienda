/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;

import co.edu.uniandes.csw.vivienda.dtos.EstudianteDetailDTO;
import co.edu.uniandes.csw.vivienda.ejb.EstudianteLogic;
import co.edu.uniandes.csw.vivienda.entities.EstudianteEntity;
import co.edu.uniandes.csw.vivienda.entities.SugerenciaEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.EstudiantePersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author e.reyesm
 */
@Path("estudiantes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
/**
 * Recurso REST de estudiante
 */
public class EstudianteResource {

    /**
     * Lógica del estudiante
     */
    @Inject
    EstudianteLogic estudianteLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    /**
     * Recurso de sugerencia
     */
    @Inject
    SugerenciaResource sugerenciaResourse; 
    /**
     * Logger para notificar estado
     */
    private static final Logger LOGGER = Logger.getLogger(EstudiantePersistence.class.getName());

    /**
     * POST http://localhost:8080/vivienda-web/api/estudiantes Ejemplo json: {
       "documento":"12345678", "nombre":"Diego", "userName":"diego123AE"}
     *
     * @param estudiante correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el documento creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "EstudianteDetailDTO", "documento": "12345678", "nombre":
     * "Diego","userName":"diego123AE"}
     * @throws BusinessLogicException
     */
    @POST
    public EstudianteDetailDTO createEstudiante(EstudianteDetailDTO estudiante) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        EstudianteEntity estudianteEntity = estudiante.toEntity();
        // Invoca la lógica para crear la bodega nueva
        EstudianteEntity nuevoEstudiante = estudianteLogic.createEstudiante(estudianteEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new EstudianteDetailDTO(nuevoEstudiante);
    }

    /**
     * GET para todas los estudiantes.
     * http://localhost:8080/vivienda-web/api/estudiantes
     *
     * @return la lista de todos los estudiantes en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<EstudianteDetailDTO> getEstudiantes() throws BusinessLogicException {
        return listEntity2DetailDTO(estudianteLogic.getEstudiantes());
    }

    /**
     * GET para un estudiante
     * http://localhost:8080/vivienda-web/api/estudiantes/12345678
     *
     * @param documento corresponde al documento del estudiante buscado.
     * @return El estudiante encontrado. Ejemplo: { "type":
     * "EstudianteDetailDTO", "documento": "12345678", "nombre":
     * "Diego","userName":"diego123AE"}
     * @throws BusinessLogicException
     */
    @GET
    @Path("{documento: \\d+}")
    public EstudianteDetailDTO getEstudiante(@PathParam("documento") Long documento) throws BusinessLogicException {
        EstudianteEntity buscado = estudianteLogic.getEstudiante(documento);
        return new EstudianteDetailDTO(buscado);

    }

    /**
     * PUT http://localhost:8080/vivienda-web/api/estudiantes/12345678 Ejemplo json: {
      "documento":"12345678", "nombre":"Diego", "userName":"diego123AE"}
     *
     * @param documento corresponde a el estudiante a actualizar.
     * @param estudiante corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return El estudiante actualizado.
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{documento: \\d+}")
    public EstudianteDetailDTO updateEstudiante(@PathParam("documento") Long documento, EstudianteDetailDTO estudiante) throws BusinessLogicException {
        
        EstudianteEntity nueva = estudiante.toEntity();
        nueva.setDocumento(documento);
        return new EstudianteDetailDTO(estudianteLogic.updateEstudiante(nueva));
    }

    /**
     * DELETE http://localhost:8080/vivienda-web/api/estudiantes/12345678
     *
     * @param documento corresponde a el estudiante a borrar.
     * @throws BusinessLogicException
     */
    @DELETE
    @Path("{documento: \\d+}")
    public void deleteEstudiante(@PathParam("documento") Long documento) throws BusinessLogicException {
        EstudianteEntity nueva = getEstudiante(documento).toEntity();
        
        for (SugerenciaEntity sugerencia : nueva.getSugerencias()) {
            Long id = sugerencia.getId();
            sugerenciaResourse.deleteSugerencia(id);
        }
        estudianteLogic.deleEstudiante(documento);
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EstudianteEntity a una lista de
     * objetos EstudianteDetailDTO (json)
     *
     * @param entityList corresponde a la lista de bodegas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de bodegaes en forma DTO (json)
     */
    private List<EstudianteDetailDTO> listEntity2DetailDTO(List<EstudianteEntity> entityList) {
        List<EstudianteDetailDTO> list = new ArrayList<>();
        for (EstudianteEntity entity : entityList) {
            list.add(new EstudianteDetailDTO(entity));
        }
        return list;
    }

}
