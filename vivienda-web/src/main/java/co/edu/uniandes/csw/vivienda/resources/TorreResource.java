/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;

import co.edu.uniandes.csw.vivienda.dtos.TorreDetailDTO;
import co.edu.uniandes.csw.vivienda.ejb.TorreLogic;
import co.edu.uniandes.csw.vivienda.entities.TorreEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author da.solano1
 */
@Path("torres")
@Produces("application/json")
@Stateless
public class TorreResource {
    @Inject 
    TorreLogic torreLogic; 
    
    @POST 
    public TorreDetailDTO createTorre(TorreDetailDTO torre)throws BusinessLogicException{
        TorreEntity entity = torre.toEntity(); 
        TorreEntity nuevoEntity = torreLogic.createTorre(entity); 
        return new TorreDetailDTO(nuevoEntity); 
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
    
    
}
