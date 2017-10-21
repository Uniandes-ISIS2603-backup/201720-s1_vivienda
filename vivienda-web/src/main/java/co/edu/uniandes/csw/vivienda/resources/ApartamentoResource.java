/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;

import co.edu.uniandes.csw.vivienda.dtos.ApartamentoDetailDTO;
import co.edu.uniandes.csw.vivienda.ejb.ApartamentoLogic;
import co.edu.uniandes.csw.vivienda.entities.ApartamentoEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
@Path("/torres/{torreId : \\d+ }/pisos/{pisoId : \\d+ }/apartamentos")
@Produces("application/json")
@Stateless 
public class ApartamentoResource {
    @Inject 
    ApartamentoLogic apartamentoLogic; 
    
    @PathParam("torreId")
    private Long torreId; 
    
    @PathParam("pisoId")
    private Long pisoId; 
    
    @POST
     public ApartamentoDetailDTO createTorre(ApartamentoDetailDTO apartamento)throws BusinessLogicException{
        ApartamentoEntity entity = apartamento.toEntity(); 
        ApartamentoEntity nuevoEntity = apartamentoLogic.createApartamento(entity); 
        return new ApartamentoDetailDTO(nuevoEntity); 
    }
    @GET 
     public List<ApartamentoDetailDTO> getApartamentos()throws BusinessLogicException{
        return listEntity2DetailDTO(apartamentoLogic.getApartamentos()); 
    }
    private List<ApartamentoDetailDTO> listEntity2DetailDTO(List<ApartamentoEntity> entityList) {
         List<ApartamentoDetailDTO> list = new ArrayList<>();
        for (ApartamentoEntity entity : entityList) {
            list.add(new ApartamentoDetailDTO(entity));
        }
        return list;
    }
    @PUT
    @Path("{id: \\d+}")
    public ApartamentoDetailDTO update(@PathParam("id") Integer id, ApartamentoDetailDTO apartamento){
        
     ApartamentoEntity apartamentoEntity = apartamentoLogic.getApartamento(id);
      if(apartamentoEntity == null){
          throw new WebApplicationException("El recurso /apartamento/" + id + " no existe.", 404); 
      }
      apartamento.setNumApartamento(id);
      ApartamentoEntity apartamentoRet = apartamento.toEntity(); 
      return new ApartamentoDetailDTO(apartamentoLogic.updateApartamento(apartamentoRet));
    }
    @DELETE 
    @Path("{id: \\d+}")
    public void delete(@PathParam("id") Integer id){
        ApartamentoEntity apartamentoEntity = apartamentoLogic.getApartamento(id);
        if(apartamentoEntity == null){
             throw new WebApplicationException("El recurso /apartamento/" + id + " no existe.", 404);
        }
        apartamentoLogic.deleteApartamento(apartamentoEntity);
    }
    @GET 
    @Path("{id: \\d+}")
    public ApartamentoDetailDTO getApartamento(@PathParam("id") Integer id){
       ApartamentoEntity apartamentoEntity = apartamentoLogic.getApartamento(id); 
       if(apartamentoEntity == null){
           throw new WebApplicationException("El recurso piso: " + id + " no existe.", 404); 
       }
       return new ApartamentoDetailDTO(apartamentoEntity); 
    }
    
}
