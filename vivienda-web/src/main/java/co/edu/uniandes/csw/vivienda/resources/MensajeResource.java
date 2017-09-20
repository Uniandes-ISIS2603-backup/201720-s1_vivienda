/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;

import co.edu.uniandes.csw.vivienda.dtos.MensajeDetailDTO;
import co.edu.uniandes.csw.vivienda.ejb.MensajeLogic;
import co.edu.uniandes.csw.vivienda.entities.MensajeEntity;
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
 * @author da.ramirezv
 */
@Path("mensajes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MensajeResource {
    
    @Inject
    MensajeLogic mensajeLogic;
    
    
    @POST
    public MensajeDetailDTO createMensaje(MensajeDetailDTO mensaje) throws Exception {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        MensajeEntity mensajeE = mensaje.toEntity();
        // Invoca la lógica para crear la editorial nueva
        MensajeEntity nuevoAdmin = mensajeLogic.createMensaje(mensajeE);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new MensajeDetailDTO(nuevoAdmin);
    }
    
    @GET
    public List<MensajeDetailDTO> getMensajes() throws Exception {
        return listEntity2DetailDTO(mensajeLogic.getMensajes());
    }
    
    @GET
    @Path("{id: \\d+}")
    public MensajeDetailDTO getMensaje(@PathParam("id") Long id) throws Exception {
        MensajeEntity entity = mensajeLogic.getMensaje(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /mensajes/" + id + " no existe.", 404);
        }
        return new MensajeDetailDTO(mensajeLogic.getMensaje(id));
    }
    
    
    @PUT
    @Path("{id: \\d+}")
    public MensajeDetailDTO updateMensaje(@PathParam("id") Long id, MensajeDetailDTO mensaje) throws Exception {
        mensaje.setId(id);
        MensajeEntity entity = mensajeLogic.getMensaje(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /mensajes/" + id + " no existe.", 404);
        }
        return new MensajeDetailDTO(mensajeLogic.updateMensaje(id, mensaje.toEntity()));
    }
    
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteMensaje(@PathParam("id") Long id) throws Exception {
        MensajeEntity entity = mensajeLogic.getMensaje(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /mensajes/" + id + " no existe.", 404);
        }
        mensajeLogic.deleteMensaje(id);
    }
    
    
     private List<MensajeDetailDTO> listEntity2DetailDTO(List<MensajeEntity> entityList) {
        List<MensajeDetailDTO> list = new ArrayList<>();
        for(MensajeEntity entity : entityList) {
            list.add(new MensajeDetailDTO(entity));
        }
        return list;
    }
}
