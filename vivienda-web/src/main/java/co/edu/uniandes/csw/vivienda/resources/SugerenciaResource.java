/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;

import co.edu.uniandes.csw.vivienda.dtos.SugerenciaDetailDTO;
import co.edu.uniandes.csw.vivienda.ejb.SugerenciaLogic;
import co.edu.uniandes.csw.vivienda.entities.SugerenciaEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.SugerenciaPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
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
@Path("sugerencias")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class SugerenciaResource {
    
    @Inject
    SugerenciaLogic sugerenciaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(SugerenciaPersistence.class.getName());

    /**
     * POST http://localhost:8080/vivienda-web/api/sugerencias Ejemplo json: {
       "id":"1", "mensaje":"Hola mundo"}
     *
     * @param sugerencia correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: {"id": "1", "mensaje":"Hola mundo",
       "estudiante":"12345678","administrador":"87654321"}
     * @throws BusinessLogicException
     */
    @POST
    public SugerenciaDetailDTO createSugerencia(SugerenciaDetailDTO sugerencia) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        SugerenciaEntity sugerenciaEntity = sugerencia.toEntity();
        // Invoca la lógica para crear la bodega nueva
        SugerenciaEntity nuevaSugerencia = sugerenciaLogic.createSugerencia(sugerenciaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new SugerenciaDetailDTO(nuevaSugerencia);
    }

    /**
     * GET para todas las sugerencias.
     * http://localhost:8080/vivienda-web/api/sugerencias
     *
     * @return la lista de todos los estudiantes en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<SugerenciaDetailDTO> getSugerencias() throws BusinessLogicException {
        return listEntity2DetailDTO(sugerenciaLogic.getSugerencias());
    }

    /**
     * GET para una sugerencia
     * http://localhost:8080/vivienda-web/api/sugerencia/1
     *
     * @param id corresponde al id de la sugerencia.
     * @return La sugerencia encontrada. Ejemplo: { "type":
     * "SugerenciaDetailDTO", "id": "1", "mensaje":"Hola mundo"}
     * @throws BusinessLogicException
     */
    @GET
    @Path("{id: \\d+}")
    public SugerenciaDetailDTO getSugerencia(@PathParam("id") Long id) throws BusinessLogicException {
        SugerenciaEntity buscado = sugerenciaLogic.getSugerencia(id);
        return new SugerenciaDetailDTO(buscado);

    }

    /**
     * PUT http://localhost:8080/vivienda-web/api/sugerencias/1 Ejemplo json: {
      "id":"1", "mensaje":"Hola mundo"}
     *
     * @param id corresponde a la sugerencia a actualizar.
     * @param sugerencia corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return la sugerencia actualizada.
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public SugerenciaDetailDTO updateSugerencia(@PathParam("id") Long id, SugerenciaDetailDTO sugerencia) throws BusinessLogicException {  
        SugerenciaEntity nueva = sugerencia.toEntity();
        SugerenciaEntity antigua = getSugerencia(id).toEntity();
        nueva.setId(id);
        nueva.setAdministrador(antigua.getAdministrador());
        nueva.setEstudiante(antigua.getEstudiante());
        
        return new SugerenciaDetailDTO(sugerenciaLogic.updateSugerencia(nueva));
    }

    /**
     * DELETE http://localhost:8080/vivienda-web/api/sugerencias/1
     *
     * @param id corresponde a la sugerencia a borrar.
     * @throws BusinessLogicException
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteSugerencia(@PathParam("id") Long id) throws BusinessLogicException {
        sugerenciaLogic.deleSugerencia(id);
      
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
    private List<SugerenciaDetailDTO> listEntity2DetailDTO(List<SugerenciaEntity> entityList) {
        List<SugerenciaDetailDTO> list = new ArrayList<>();
        for (SugerenciaEntity entity : entityList) {
            list.add(new SugerenciaDetailDTO(entity));
        }
        return list;
    }

}
