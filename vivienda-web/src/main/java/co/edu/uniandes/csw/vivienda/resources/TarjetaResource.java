/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;

import co.edu.uniandes.csw.vivienda.dtos.TarjetaDetailDTO;
import co.edu.uniandes.csw.vivienda.ejb.TarjetaLogic;
import co.edu.uniandes.csw.vivienda.entities.TarjetaEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.TarjetaPersistence;
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
 * Clase que implementa el recurso REST correspondiente a "tarjeta".
 * @author mp.franco10
 */
@Path("tarjetas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TarjetaResource {
    
    @Inject
    TarjetaLogic tarjetaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(TarjetaPersistence.class.getName());
    
        /**
     * @param tarjeta correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. 
     * @throws BusinessLogicException
     */
    @POST
    public TarjetaDetailDTO createTarjeta(TarjetaDetailDTO tarjeta) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        TarjetaEntity tarjetaEntity = tarjeta.toEntity();
        // Invoca la lógica para crear la editorial nueva
        TarjetaEntity nuevaTarjeta = tarjetaLogic.createTarjeta(tarjetaEntity);
        if(tarjetaEntity.getCuenta()!=null)
        {
            List<TarjetaEntity> tarjetas = tarjetaEntity.getCuenta().getTarjeta();
            tarjetas.add(nuevaTarjeta);
            tarjetaEntity.getCuenta().setTarjeta(tarjetas);
        }
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new TarjetaDetailDTO(nuevaTarjeta);
    }
    
        /**
     * GET para todas las tarjetas.
     *
     * @return la lista de todas las tarjetas en objetos json DTO.
     * @throws Exception
     */
    @GET
    public List<TarjetaDetailDTO> getTarjetas() throws BusinessLogicException {
        return listEntity2DetailDTO(tarjetaLogic.getTarjetas());
    }
    
    /**
     * GET para una tarjeta
     *
     * @param numeroTarjeta corresponde al id de la editorial buscada.
     * @return La tarjeta encontrada. 
     * @throws Exception
     *
     * En caso de no existir el id de la tarjeta buscada se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{numeroTarjeta: \\d+}")
    public TarjetaDetailDTO getTarjeta(@PathParam("numeroTarjeta") Long numeroTarjeta) throws BusinessLogicException {
        TarjetaEntity entity = tarjetaLogic.getTarjeta(numeroTarjeta);
        if (entity == null) {
            throw new WebApplicationException("El recurso /tarjetas/" + numeroTarjeta + " no existe.", 404);
        }
        return new TarjetaDetailDTO(tarjetaLogic.getTarjeta(numeroTarjeta));
    }
    
       /**
     * @param numeroTarjeta corresponde a la tarjeta a actualizar.
     * @param tarjeta corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La tarjeta actualizada.
     * @throws Exception
     *
     * En caso de no existir el id de la tarjeta a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{numeroTarjeta: \\d+}")
    public TarjetaDetailDTO updateTarjeta(@PathParam("numeroTarjeta") Long numeroTarjeta, TarjetaDetailDTO tarjeta) throws BusinessLogicException {
        tarjeta.setNumeroTarjeta(numeroTarjeta);
        TarjetaEntity entity = tarjetaLogic.getTarjeta(numeroTarjeta);
        if (entity == null) {
            throw new WebApplicationException("El recurso /tarjetas/" + numeroTarjeta + " no existe.", 404);
        }
        return new TarjetaDetailDTO(tarjetaLogic.updateTarjeta(numeroTarjeta, tarjeta.toEntity()));
    }
    
       /**
     * DELETE 
     * @param numeroTarjeta corresponde a la editorial a borrar.
     * @throws Exception
     *
     * En caso de no existir el id de la tarjeta a borrar se retorna un
     * 404 con el mensaje.
     * @throws java.sql.SQLException
     *
     */
    @DELETE
    @Path("{numeroTarjeta: \\d+}")
    public void deleteTarjeta(@PathParam("numeroTarjeta") Long numeroTarjeta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una tarjeta con id {0}", numeroTarjeta);
        TarjetaEntity entity =tarjetaLogic.getTarjeta(numeroTarjeta);
        if (entity == null) {
            throw new WebApplicationException("El recurso /tarjetas/" + numeroTarjeta + " no existe.", 404);
        }
        tarjetaLogic.deleteTarjeta(numeroTarjeta);

    }

     /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos TarjetaEntity a una lista de
     * objetos TarjetaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de tarjetas de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de tarjetas en forma DTO (json)
     */
    private List<TarjetaDetailDTO> listEntity2DetailDTO(List<TarjetaEntity> entityList) {
        List<TarjetaDetailDTO> list = new ArrayList<>();
        for (TarjetaEntity entity : entityList) {
            list.add(new TarjetaDetailDTO(entity));
        }
        return list;
    }

}
