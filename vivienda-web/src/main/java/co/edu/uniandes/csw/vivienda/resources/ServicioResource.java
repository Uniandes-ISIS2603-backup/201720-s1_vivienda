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
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author rj.gonzalez10
 */
public class ServicioResource {
     @Inject
     ServicioLogic servicioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @POST
    public ServicioDetailDTO createServicio(ServicioDetailDTO city) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ServicioEntity cityEntity = city.toEntity();
        // Invoca la lógica para crear el servicio nuevo
        ServicioEntity nuevoServicio = servicioLogic.createServicio(cityEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new ServicioDetailDTO(nuevoServicio);
    }

    @GET
    public List<ServicioDetailDTO> getServices() throws BusinessLogicException {
        return listEntity2DetailDTO(servicioLogic.getServicios());
    }

    @GET
    @Path("{id: \\d+}")
    public ServicioDetailDTO getCity(@PathParam("id") String id) {
        ServicioEntity entity = servicioLogic.getServicio(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso Servicio: " + id + " no existe.", 404);
        }
        return new ServicioDetailDTO(entity);
    }

    @PUT
    @Path("{id: \\d+}")
    public ServicioDetailDTO updateCity(@PathParam("id") String id, ServicioDetailDTO servicio) throws BusinessLogicException {
        servicio.setNombre(id);
        ServicioEntity entity = servicioLogic.getServicio(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso Servicio: " + id + " no existe.", 404);
        }
        return new ServicioDetailDTO(servicioLogic.updateCity(id, servicio.toEntity()));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteCity(@PathParam("id") String id) {
        ServicioEntity entity = servicioLogic.getServicio(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso city: " + id + " no existe.", 404);
        }
        servicioLogic.deleteCity(entity);
    }

    
    private List<ServicioDetailDTO> listEntity2DetailDTO(List<ServicioEntity> entityList) {
        List<ServicioDetailDTO> list = new ArrayList<>();
        for (ServicioEntity entity : entityList) {
            list.add(new ServicioDetailDTO(entity));
        }
        return list;
    }
}
