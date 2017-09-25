/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;

import co.edu.uniandes.csw.vivienda.dtos.PisoDetailDTO;
import co.edu.uniandes.csw.vivienda.ejb.PisoLogic;
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
@Path("pisos")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class PisoResource {
    @Inject 
    PisoLogic pisoLogic; 
    
    @POST 
    public PisoDetailDTO createPiso(PisoDetailDTO piso)throws BusinessLogicException{
        PisoEntity entity = piso.toEntity(); 
        PisoEntity nuevoEntity = pisoLogic.createPiso(entity); 
        return new PisoDetailDTO(nuevoEntity); 
    }
    
    @GET 
    public List<PisoDetailDTO> getPisos()throws BusinessLogicException{
        return listEntity2DetailDTO(pisoLogic.getPisos()); 
    }

    private List<PisoDetailDTO> listEntity2DetailDTO(List<PisoEntity> entityList) {
         List<PisoDetailDTO> list = new ArrayList<>();
        for (PisoEntity entity : entityList) {
            list.add(new PisoDetailDTO(entity));
        }
        return list;
    }
    
    @PUT
    @Path("{id: \\d+}")
    public PisoDetailDTO update(@PathParam("id") Integer id, PisoDetailDTO piso){
        
     PisoEntity pisoEntity = pisoLogic.getPiso(id);
      if(pisoEntity == null){
          throw new WebApplicationException("El recurso /piso/" + id + " no existe.", 404); 
      }
      piso.setId(id);
      PisoEntity pisoRet = piso.toEntity(); 
      return new PisoDetailDTO(pisoLogic.updatePiso(pisoRet));
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
}
