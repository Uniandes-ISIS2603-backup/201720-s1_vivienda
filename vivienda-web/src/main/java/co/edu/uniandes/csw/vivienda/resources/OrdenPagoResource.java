/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;

import co.edu.uniandes.csw.vivienda.dtos.OrdenPagoDetailDTO;
import co.edu.uniandes.csw.vivienda.ejb.OrdenPagoLogic;
import co.edu.uniandes.csw.vivienda.entities.OrdenPagoEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.OrdenPagoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
 * @author e.reyesm
 */
@Path("ordenPagos")
@Produces("application/json")
@Consumes("application/json")
public class OrdenPagoResource {
    
    @Inject
    OrdenPagoLogic ordenPagoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(OrdenPagoPersistence.class.getName());
    
     /**
     * Id de la cuenta a la que pertenece la orden de pago
     */


    /**
     * POST http://localhost:8080/vivienda-web/api/ordenPagos Ejemplo json: {
       "idPago":"1", "precio":"2300"}
     *
     * @param ordenPago correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el idPago creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "OrdenPagoDetailDTO", "idPago": "1", "precio":"2300"}
     * @throws BusinessLogicException
     */
    @POST
    public OrdenPagoDetailDTO createOrdenPago(OrdenPagoDetailDTO ordenPago) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        OrdenPagoEntity ordenPagoEntity = ordenPago.toEntity();
        // Invoca la lógica para crear la bodega nueva
        OrdenPagoEntity nuevaOrdenPago = ordenPagoLogic.createOrdenPago(ordenPagoEntity);
        if(nuevaOrdenPago!=null){
       if(ordenPagoEntity.getCuenta()!=null)
        {
            List<OrdenPagoEntity> ordenes = ordenPagoEntity.getCuenta().getOrdenPagos();
            ordenes.add(nuevaOrdenPago);
            ordenPagoEntity.getCuenta().setOrdenPagos(ordenes);
        }}
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new OrdenPagoDetailDTO(nuevaOrdenPago);
    }

    /**
     * GET para todas las ordenes de pagos.
     * http://localhost:8080/vivienda-web/api/ordenPagos
     *
     * @return la lista de todos las ordenes de pagos en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    @Path("{estadoPago}")
    public List<OrdenPagoDetailDTO> getOrdenesPagos(@PathParam("estadoPago") String estado) throws BusinessLogicException {
        Boolean state = null;
        if(estado.equals("pagados")){
            state = true;
            List<OrdenPagoEntity> lista = new ArrayList<OrdenPagoEntity>();
            for (OrdenPagoEntity ordenPago : ordenPagoLogic.getOrdenesPagos()) {
                if(ordenPago.isPagada())
                lista.add(ordenPago);
            }
            return listEntity2DetailDTO(lista);
        }
        else if(estado.equals("noPagados"))
        { 
            List<OrdenPagoEntity> lista = new ArrayList<OrdenPagoEntity>();
            for (OrdenPagoEntity ordenPago : ordenPagoLogic.getOrdenesPagos()) {
                if(!ordenPago.isPagada())
                lista.add(ordenPago);
            }
            return listEntity2DetailDTO(lista);
        }
        else
        {
            throw new WebApplicationException("El recurso /ordenPagos/" + estado + " no existe.", 404); 
        } 
    }

    /**
     * GET para una orden de pago
     * http://localhost:8080/vivienda-web/api/ordenPagos/1
     *
     * @param idPago corresponde al id de la orden de pago.
     * @return La orden de pago encontrada. Ejemplo: { "type":
     * "SugerenciaDetailDTO", "id": "1", "mensaje":"Hola mundo"}
     * @throws BusinessLogicException
     */
    @GET
    @Path("{idPago: \\d+}")
    public OrdenPagoDetailDTO getOrdenPago(@PathParam("idPago") Long idPago) throws BusinessLogicException {
        OrdenPagoEntity buscado = ordenPagoLogic.getOrdenPago(idPago);
         if (buscado == null) {
            throw new WebApplicationException("El recurso /cuentas/" + idPago + " no existe.", 404);
        }
        return new OrdenPagoDetailDTO(buscado);

    }

    /**
     * PUT http://localhost:8080/vivienda-web/api/ordenPagos/1 Ejemplo json: {
      "idPago":"1", "precio":"2300"}
     *
     * @param idPago corresponde a la orden de pago a actualizar.
     * @param ordenPago corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return la orden de pago actualizada.
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{idPago: \\d+}")
    public OrdenPagoDetailDTO updateOrdenPago(@PathParam("idPago") Long idPago, OrdenPagoDetailDTO ordenPago) throws BusinessLogicException {
 
        return new OrdenPagoDetailDTO(ordenPagoLogic.updateOrdenPago(ordenPago.toEntity()));
    }

    /**
     * DELETE http://localhost:8080/vivienda-web/api/ordenPagos/1
     *
     * @param idPago corresponde a la sugerencia a borrar.
     * @throws BusinessLogicException
     */
    @DELETE
    @Path("{idPago: \\d+}")
    public void deleteOrdenPago(@PathParam("idPago") Long idPago) throws BusinessLogicException {
        ordenPagoLogic.deleteOrdenPago(idPago);
    }
    
    @GET
    public List<OrdenPagoDetailDTO> getOrdenPagos() throws BusinessLogicException {
        return listEntity2DetailDTO(ordenPagoLogic.getOrdenesPagos());
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EstudianteEntity a una lista de
     * objetos EstudianteDetailDTO (json)
     *
     * @param entityList corresponde a la lista de bodegas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de bodegaes en forma DTO (json)
     */
    private List<OrdenPagoDetailDTO> listEntity2DetailDTO(List<OrdenPagoEntity> entityList) {
        List<OrdenPagoDetailDTO> list = new ArrayList<>();
        for (OrdenPagoEntity entity : entityList) {
            list.add(new OrdenPagoDetailDTO(entity));
        }
        return list;
    }

}
