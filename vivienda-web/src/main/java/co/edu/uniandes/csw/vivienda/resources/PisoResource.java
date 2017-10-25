/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;

import co.edu.uniandes.csw.vivienda.dtos.PisoDTO;
import co.edu.uniandes.csw.vivienda.dtos.PisoDetailDTO;
import co.edu.uniandes.csw.vivienda.ejb.PisoLogic;
import co.edu.uniandes.csw.vivienda.ejb.TorreLogic;
import co.edu.uniandes.csw.vivienda.entities.PisoEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
@Path("/torres/{torreId : \\d+ }/piso")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class PisoResource {
    @Inject 
    PisoLogic pisoLogic;
    TorreLogic torreLogic;
    @PathParam("torreId")
    private Integer torreId; 
    
    @POST 
    public PisoDTO createPiso(PisoDTO piso)throws BusinessLogicException{
        PisoEntity entity = piso.toEntity(); 
        PisoEntity nuevoEntity = pisoLogic.createPiso(entity); 
        return new PisoDTO(nuevoEntity); 
    }
    
   // @GET 
    //public List<PisoDetailDTO> getPisos(@PathParam("id") Integer id)throws BusinessLogicException{
     //   return listEntity2DetailDTO(torreLogic.getPisos(id)); 
   // }

    private List<PisoDetailDTO> listEntity2DetailDTO(List<PisoEntity> entityList) {
         List<PisoDetailDTO> list = new ArrayList<>();
        for (PisoEntity entity : entityList) {
            list.add(new PisoDetailDTO(entity));
        }
        return list;
    }
    
    @GET 
    @Path("{id: \\d+}")
    public PisoDetailDTO getPiso(@PathParam("id") Integer id){
       PisoEntity pisoEntity = pisoLogic.getPiso(id); 
       if(pisoEntity == null){
           throw new WebApplicationException("El recurso piso: " + id + " no existe.", 404); 
       }
       return new PisoDetailDTO(pisoEntity); 
    }
    @PUT
    @Path("{id: \\d+}")
    public PisoDTO update(@PathParam("id") Integer id, PisoDTO piso){
        
     PisoEntity pisoEntity = pisoLogic.getPiso(id);
      if(pisoEntity == null){
          throw new WebApplicationException("El recurso /piso/" + id + " no existe.", 404); 
      }
      piso.setId(id);
      PisoEntity pisoRet = piso.toEntity(); 
      return new PisoDTO(torreLogic.updatePiso(torreId,pisoRet));
    }
    
    @DELETE 
    @Path("{id: \\d+}")
    public void delete(@PathParam("id") Integer id){
        PisoEntity pisoEntity = pisoLogic.getPiso(id);
        if(pisoEntity == null){
             throw new WebApplicationException("El recurso /piso/" + id + " no existe.", 404);
        }
        pisoLogic.deletePiso(pisoEntity);
    }
    @GET
    @Path("{id : \\d+}/apartamentos")
    public Class<ApartamentoResource> getApartamentos(){
        List<PisoEntity> lista = pisoLogic.getPisos();  
        if (lista.isEmpty()) {
            throw new WebApplicationException("El recurso /Torre/ no existe.", 404);
        }
        return ApartamentoResource.class;
    }
}
