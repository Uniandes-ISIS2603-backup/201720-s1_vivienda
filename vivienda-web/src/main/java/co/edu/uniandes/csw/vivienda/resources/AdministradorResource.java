/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;

import co.edu.uniandes.csw.vivienda.dtos.AdministradorDetailDTO;
import co.edu.uniandes.csw.vivienda.ejb.AdministradorLogic;
import co.edu.uniandes.csw.vivienda.entities.AdministradorEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author da.ramirezv
 */
@Path("administradores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AdministradorResource {
    
    @Inject
    AdministradorLogic adminLogic;
    
    @POST
    public AdministradorDetailDTO createAdministrador(AdministradorDetailDTO admin) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        AdministradorEntity adminE = admin.toEntity();
        // Invoca la lógica para crear la editorial nueva
        AdministradorEntity nuevoAdmin = adminLogic.createAdministrador(adminE);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new AdministradorDetailDTO(nuevoAdmin);
    }
    
    @GET
    public List<AdministradorDetailDTO> getAdministradores() throws BusinessLogicException {
        return listEntity2DetailDTO(adminLogic.getAdministradores());
    }
    
    @GET
    @Path("{id: \\d+}")
    public AdministradorDetailDTO getAdministrador(@PathParam("id") Long id) throws BusinessLogicException {
        AdministradorEntity entity = adminLogic.getAdministrador(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /administradores/" + id + " no existe.", 404);
        }
        return new AdministradorDetailDTO(adminLogic.getAdministrador(id));
    }
    
    
    @PUT
    @Path("{id: \\d+}")
    public AdministradorDetailDTO updateAdministrador(@PathParam("id") Long id, AdministradorDetailDTO admin) throws BusinessLogicException {
        admin.setDocumento(id);
        AdministradorEntity entity = adminLogic.getAdministrador(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /administradores/" + id + " no existe.", 404);
        }
        return new AdministradorDetailDTO(adminLogic.updateAdministrador(id, admin.toEntity()));
    }
    
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteAdministrador(@PathParam("id") Long id) throws BusinessLogicException {
        AdministradorEntity entity = adminLogic.getAdministrador(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /administradores/" + id + " no existe.", 404);
        }
        adminLogic.deleteAdministrador(id);
    }
    
    
     private List<AdministradorDetailDTO> listEntity2DetailDTO(List<AdministradorEntity> entityList) {
        List<AdministradorDetailDTO> list = new ArrayList<>();
        for(AdministradorEntity entity : entityList) {
            list.add(new AdministradorDetailDTO(entity));
        }
        return list;
    }
}
