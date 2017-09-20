/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;
import co.edu.uniandes.csw.vivienda.dtos.PagoDetailDTO;
import co.edu.uniandes.csw.vivienda.ejb.PagoLogic;
import co.edu.uniandes.csw.vivienda.entities.PagoEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.PagoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * Clase que implementa el recurso REST correspondiente a "editorials".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "editorials". Al ejecutar la aplicación, el
 * recurso será accesibe a través de la ruta "/api/editorials"
 *
 * @author ISIS2603
 *
 */
@Path("pagos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PagoResource {
    
     @Inject
    PagoLogic pagoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(PagoPersistence.class.getName());

    /**
     * POST
     * @param pago correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. 
     * @throws BusinessLogicException
     */
    @POST
    public PagoDetailDTO createPago(PagoDetailDTO pago) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        PagoEntity pagoEntity = pago.toEntity();
        // Invoca la lógica para crear el pago nueva
        PagoEntity nuevoPago = pagoLogic.createPago(pagoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new PagoDetailDTO(nuevoPago);
    }

    /**
     * GET para todos los pagos
     * @return la lista de todos los pagos en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<PagoDetailDTO> getPagos() throws BusinessLogicException {
        return listEntity2DetailDTO(pagoLogic.getPagos());
    }

    /**
     * GET para un pago
     * @param numeroPago corresponde al id del pago buscad0.
     * @return El pago encontrad0. 
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del pago buscada se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{numeroPago: \\d+}")
    public PagoDetailDTO getPago(@PathParam("numeroPago") Long numeroPago) throws BusinessLogicException {
        PagoEntity entity = pagoLogic.getPago(numeroPago);
        if (entity == null) {
            throw new WebApplicationException("El recurso /pagos/" + numeroPago + " no existe.", 404);
        }
        return new PagoDetailDTO(pagoLogic.getPago(numeroPago));
    }

    /**
     * PUT 
     * @param numeroPago corresponde al pago a actualizar.
     * @param pago corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return El pago actualizado.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del pago a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{numeroPago: \\d+}")
    public PagoDetailDTO updatePago(@PathParam("numeroPago") Long numeroPago, PagoDetailDTO pago) throws BusinessLogicException {
        pago.setNumeroPago(numeroPago);
        PagoEntity entity = pagoLogic.getPago(numeroPago);
        if (entity == null) {
            throw new WebApplicationException("El recurso /pagos/" + numeroPago + " no existe.", 404);
        }
        return new PagoDetailDTO(pagoLogic.updatePago(numeroPago, pago.toEntity()));
    }

    /**
     * DELETE 
     * @param numeroPago corresponde al pago a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del pago a actualizar se retorna un
     * 404 con el mensaje.
     * @throws java.sql.SQLException
     *
     */
    @DELETE
    @Path("{numeroPago: \\d+}")
    public void deletePago(@PathParam("numeroPago") Long numeroPago) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una pago con id {0}", numeroPago);
        PagoEntity entity = pagoLogic.getPago(numeroPago);
        if (entity == null) {
            throw new WebApplicationException("El recurso /pagos/" + numeroPago + " no existe.", 404);
        }
        pagoLogic.deletePago(numeroPago);

    }
    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PagoEntity a una lista de
     * objetos PagoDetailDTO (json)
     *
     * @param entityList corresponde a la lista de paos de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de pagos en forma DTO (json)
     */
    private List<PagoDetailDTO> listEntity2DetailDTO(List<PagoEntity> entityList) {
        List<PagoDetailDTO> list = new ArrayList<>();
        for (PagoEntity entity : entityList) {
            list.add(new PagoDetailDTO(entity));
        }
        return list;
    }

}


