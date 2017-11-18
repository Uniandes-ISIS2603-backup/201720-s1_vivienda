/*
 * Clase que representa los apartamentos de la vivienda
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
@Path("/torres/{torreId : \\d+ }/piso/{pisoId : \\d+ }/apartamento")
@Produces("application/json")
@Stateless 
public class ApartamentoResource {
    
    /**
     * Lógica de apartamentos
     */
    @Inject 
    ApartamentoLogic apartamentoLogic; 
    /**
     * Parámetro en la ruta del id de la torre
     */
    @PathParam("torreId")
    private Long torreId; 
    /**
     * Parámetro en la ruta del id del piso
     */
    @PathParam("pisoId")
    private Long pisoId; 
    /**
     * Crear un nuevo apartamento
     * @param apartamento a crear
     * @return apartamento creado
     * @throws BusinessLogicException si se presenta un error creando el apartamento
     */
    @POST
     public ApartamentoDetailDTO createTorre(ApartamentoDetailDTO apartamento)throws BusinessLogicException{
        ApartamentoEntity entity = apartamento.toEntity(); 
        ApartamentoEntity nuevoEntity = apartamentoLogic.createApartamento(entity); 
        return new ApartamentoDetailDTO(nuevoEntity); 
    }
     /**
      * Retorna los apartamentos de vivienda
      * @return lista de los apartamentos
      * @throws BusinessLogicException  Si se presenta un error obteniendo los apartamentos
      */
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
    /**
     * Actualiza el apartamento con id dado
     * @param id del apartamento a actualizar
     * @param apartamento detail dto del apartamento
     * @return apartamento modificado
     */
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
    /**
     * Borra el apartamento con id dado
     * @param id del apartamento a eliminar
     */
    @DELETE 
    @Path("{id: \\d+}")
    public void delete(@PathParam("id") Integer id){
        ApartamentoEntity apartamentoEntity = apartamentoLogic.getApartamento(id);
        if(apartamentoEntity == null){
             throw new WebApplicationException("El recurso /apartamento/" + id + " no existe.", 404);
        }
        apartamentoLogic.deleteApartamento(apartamentoEntity);
    }
    /**
     * Retorna el apartamento con id dado
     * @param id del apartamento a buscar
     * @return  apartamento con id dado
     */
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
