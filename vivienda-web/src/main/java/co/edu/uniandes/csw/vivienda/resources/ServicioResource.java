/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;

import co.edu.uniandes.csw.vivienda.dtos.ServicioDetailDTO;
import co.edu.uniandes.csw.vivienda.ejb.ServicioLogic;
import co.edu.uniandes.csw.vivienda.entities.ServicioEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.ServicioPersistence;
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
 * @author rj.gonzalez10
 */
@Path("servicios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ServicioResource {
    @Inject
    ServicioLogic servicioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(ServicioPersistence.class.getName());

    /**
     * POST http://localhost:8080/vivienda-web/api/servicios Ejemplo json: {
       "id":"1", "mensaje":"Hola mundo"}
     *
     * @param sugerencia correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "SugerenciaDetailDTO", "id": "1", "mensaje":"Hola mundo"}
     * @throws BusinessLogicException
     */
    @POST
    public ServicioDetailDTO createServicio(ServicioDetailDTO sugerencia) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ServicioEntity servicioEntity = sugerencia.toEntity();
        // Invoca la lógica para crear la bodega nueva
        ServicioEntity nuevaSugerencia = servicioLogic.createServicio(servicioEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new ServicioDetailDTO(nuevaSugerencia);
    }

    /**
     * GET para todas las sugerencias.
     * http://localhost:8080/vivienda-web/api/servicios
     *
     * @return la lista de todos los estudiantes en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<ServicioDetailDTO> getServicios() throws BusinessLogicException {
        return listEntity2DetailDTO(servicioLogic.getServicios());
    }

    /**
     * GET para una sugerencia
     * http://localhost:8080/vivienda-web/api/servicios/1
     *
     * @param id corresponde al id de la sugerencia.
     * @return La sugerencia encontrada. Ejemplo: { "type":
     * "SugerenciaDetailDTO", "id": "1", "mensaje":"Hola mundo"}
     * @throws BusinessLogicException
     */
    @GET
    @Path("{id: \\d+}")
    public ServicioDetailDTO getServicio(@PathParam("id") String id) throws BusinessLogicException {
        ServicioEntity buscado = servicioLogic.getServicio(id);
        return new ServicioDetailDTO(buscado);

    }

    /**
     * PUT http://localhost:8080/vivienda-web/api/servicios/1 Ejemplo json: {
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
    public ServicioDetailDTO updateServicio(@PathParam("id") String id, ServicioDetailDTO sugerencia) throws BusinessLogicException {
        ServicioEntity nueva = new ServicioEntity();
        nueva.setNombre(id);
        nueva.setPrecio(sugerencia.getPrecio());
        
        return new ServicioDetailDTO(servicioLogic.updateCity(id, nueva));
    }

    /**
     * DELETE http://localhost:8080/vivienda-web/api/servicios/1
     *
     * @param id corresponde a la sugerencia a borrar.
     * @throws BusinessLogicException
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteServicio(@PathParam("id") String id) throws BusinessLogicException {
        servicioLogic.deleteCity(servicioLogic.getServicio(id));
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
    private List<ServicioDetailDTO> listEntity2DetailDTO(List<ServicioEntity> entityList) {
        List<ServicioDetailDTO> list = new ArrayList<>();
        for (ServicioEntity entity : entityList) {
            list.add(new ServicioDetailDTO(entity));
        }
        return list;
    }

}


