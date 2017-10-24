/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;

import co.edu.uniandes.csw.vivienda.dtos.TorreDetailDTO;
import co.edu.uniandes.csw.vivienda.dtos.TorreDTO;
import co.edu.uniandes.csw.vivienda.ejb.PisoLogic;
import co.edu.uniandes.csw.vivienda.ejb.TorreLogic;
import co.edu.uniandes.csw.vivienda.entities.TorreEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
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
 * @author da.solano1
 */
@Path("/torres")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class TorreResource {
    
    private static final Logger LOGGER = Logger.getLogger(TorreResource.class.getName());
    @Inject 
    TorreLogic torreLogic; 
    @Inject
    PisoLogic pisoLogic;
    
    @Path("{id: \\d+}/pisos")
    public Class<TorrePisoResource> getPisos(@PathParam("id") Integer id) {
        LOGGER.log(Level.SEVERE, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHH");
        TorreEntity entity = torreLogic.getTorre(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /reservas/" + id + "/accesorios no existe.", 404);
        }
        return TorrePisoResource.class;
    }
    
    @POST 
    public TorreDTO createTorre(TorreDTO torre)throws BusinessLogicException{
        TorreEntity entity = torre.toEntity(); 
        TorreEntity nuevoEntity = torreLogic.createTorre(entity); 
        return new TorreDTO(nuevoEntity); 
    }
    @GET 
    public List<TorreDetailDTO> getTorres()throws BusinessLogicException{
        return listEntity2DetailDTO(torreLogic.getTorres()); 
    }

    private List<TorreDetailDTO> listEntity2DetailDTO(List<TorreEntity> entityList) {
         List<TorreDetailDTO> list = new ArrayList<>();
        for (TorreEntity entity : entityList) {
            list.add(new TorreDetailDTO(entity));
        }
        return list;
    }
    @GET 
    @Path("{id: \\d+}")
    public TorreDetailDTO getTorre(@PathParam("id") Integer id){
       TorreEntity torreEntity = torreLogic.getTorre(id);
       if(torreEntity == null){
           throw new WebApplicationException("El recurso torre: " + id + " no existe.", 404); 
       }
       return new TorreDetailDTO(torreEntity); 
    }
    @PUT
    @Path("{id: \\d+}")
    public TorreDetailDTO update(@PathParam("id") Integer id, TorreDetailDTO torre){
        
     TorreEntity torreEntity = torreLogic.getTorre(id);
      if(torreEntity == null){
          throw new WebApplicationException("El recurso /torre/" + id + " no existe.", 404); 
      }
      torre.setId(id);
      TorreEntity torreRet = torre.toEntity(); 
      return new TorreDetailDTO(torreLogic.updateTorre(torreRet));
    }
    @DELETE 
    @Path("{id: \\d+}")
    public void delete(@PathParam("id") Integer id){
        TorreEntity torreEntity = torreLogic.getTorre(id);
        if(torreEntity == null){
             throw new WebApplicationException("El recurso /torre/" + id + " no existe.", 404);
        }
        torreLogic.delete(torreEntity);
    }
   // @GET
   // @Path("{id : \\d+}/pisos")
   // public Class<PisoResource> getPiso(@PathParam("id") Integer id){
      //  TorreEntity entity = torreLogic.getTorre(id); 
       // if (entity == null) {
         //   throw new WebApplicationException("El recurso /Torre/" + id + " no existe.", 404);
        //}
        //return PisoResource.class;
   // }
    
    
}
