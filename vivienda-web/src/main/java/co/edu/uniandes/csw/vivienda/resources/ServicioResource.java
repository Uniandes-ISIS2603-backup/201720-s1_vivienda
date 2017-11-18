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
import java.util.ArrayList;
import java.util.List;
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
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author rj.gonzalez10
 */

@Path("servicios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ServicioResource {
    
    /**
     * Lógica de servicio
     */
    @Inject
    ServicioLogic servicioLogic;
    
    /**
     * POST de un servicio
     * @param servicio a crear
     * @return servicio creado
     * @throws BusinessLogicException si ya existía el servicio a crear
     */ 
    @POST
    public ServicioDetailDTO createServicio(ServicioDetailDTO servicio) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ServicioEntity servicioE = servicio.toEntity();
        // Invoca la lógica para crear la editorial nueva
       ServicioEntity nuevoAdmin = servicioLogic.createServicio(servicioE);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new ServicioDetailDTO(nuevoAdmin);
    }
    /**
     * GET de todos los servicios
     * @return lista de servicios
     * @throws BusinessLogicException si se presenta un error
     */
    @GET
    public List<ServicioDetailDTO> getServicios() throws BusinessLogicException {
        return listEntity2DetailDTO(servicioLogic.getServicios());
    }
    /**
     * GET de un servicio en particular
     * @param id del servicio a obtener
     * @return servicio con id dado
     * @throws BusinessLogicException si el servicio no existe
     */
    @GET
    @Path("{name}")
    public ServicioDetailDTO getServicio(@PathParam("name") String id) throws BusinessLogicException {
        ServicioEntity entity = servicioLogic.getServicio(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /servicios/" + id + " no existe.", 404);
        }
        return new ServicioDetailDTO(servicioLogic.getServicio(id));
    }
    
    /**
     * PUT
     * @param id del servicio a actualizar
     * @param servicio con los nuevos datos
     * @return servicio modificado
     * @throws BusinessLogicException  si el servicio no existía
     */
    @PUT
    @Path("{name}")
    public ServicioDetailDTO updateServicio(@PathParam("name") String id, ServicioDetailDTO servicio) throws BusinessLogicException {
  
        ServicioEntity entity = servicioLogic.getServicio(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /mensajes/" + id + " no existe.", 404);
        }
        return new ServicioDetailDTO(servicioLogic.updateCity(id, servicio.toEntity()));
    }
    
    /**
     * DELETE
     * @param id del servicio a borrar
     * @throws BusinessLogicException si el servicio no existía
     */
    @DELETE
    @Path("{name}")
    public void deleteServicio(@PathParam("name") String id) throws BusinessLogicException {
        ServicioEntity entity = servicioLogic.getServicio(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /mensajes/" + id + " no existe.", 404);
        }
        servicioLogic.deleteCity(entity);
    }
    
    /**
     * Convierte una lista de entidades a una lista de DetailDTO
     * @param entityList lista de entidades
     * @return lista de DetailDTO
     */
     private List<ServicioDetailDTO> listEntity2DetailDTO(List<ServicioEntity> entityList) {
        List<ServicioDetailDTO> list = new ArrayList<>();
        for(ServicioEntity entity : entityList) {
            list.add(new ServicioDetailDTO(entity));
        }
        return list;
    }
}
