/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;

import co.edu.uniandes.csw.vivienda.dtos.PrestadorDetailDTO;
import co.edu.uniandes.csw.vivienda.ejb.PrestadorLogic;
import co.edu.uniandes.csw.vivienda.entities.PrestadorEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.PrestadorPersistence;
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
 *
 * @author rj.gonzalez10
 */
@Path("prestadores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class PrestadorResourse {
    /**
     * Lógica del prestador
     */
     @Inject
    PrestadorLogic prestadorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    /**
     * Logger para imprimir el estado de los métodos
     */
    private static final Logger LOGGER = Logger.getLogger(PrestadorPersistence.class.getName());
    
        /**
     * @param prestador correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "editorialDetailDTO", "id": 1, "name": "Norma" }
     * @throws BusinessLogicException
     */
    @POST
    public PrestadorDetailDTO createTarjeta(PrestadorDetailDTO prestador) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        PrestadorEntity prestadorEntity = prestador.toEntity();
        // Invoca la lógica para crear la editorial nueva
        PrestadorEntity nuevaTarjeta = prestadorLogic.createServicio(prestadorEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new PrestadorDetailDTO(nuevaTarjeta);
    }
    
    /**
     * GET para todos los préstamos.
     *
     * @return la lista de todos los prestadores en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<PrestadorDetailDTO> getPrestadores() throws BusinessLogicException {
        return listEntity2DetailDTO(prestadorLogic.getServicios());
    }
    
    /**
     * GET para un presador
     *
     * @param numeroTarjeta corresponde al id del prestador buscada.
     * @return El prestador encontrado. 
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del prestador buscado se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{documento: \\d+}")
    public PrestadorDetailDTO getTarjeta(@PathParam("documento") Long numeroTarjeta) throws BusinessLogicException {
        PrestadorEntity entity = prestadorLogic.getServicio(numeroTarjeta);
        if (entity == null) {
            throw new WebApplicationException("El recurso /tarjetas/" + numeroTarjeta + " no existe.", 404);
        }
        return new PrestadorDetailDTO(prestadorLogic.getServicio(numeroTarjeta));
    }
    
     /** PUT
     * @param documento corresponde al documento del prestador a modificar.
     * @param prestador corresponde al prestador con lso nuevos datos
     * @return El prestador actualizado
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la tarjeta a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{documento: \\d+}")
    public PrestadorDetailDTO updateTarjeta(@PathParam("documento") Long documento, PrestadorDetailDTO prestador) throws BusinessLogicException {
        prestador.setDocumento(documento);
        PrestadorEntity entity = prestadorLogic.getServicio(documento);
        if (entity == null) {
            throw new WebApplicationException("El recurso /tarjetas/" + documento + " no existe.", 404);
        }
        return new PrestadorDetailDTO(prestadorLogic.updatePrestador(documento, prestador.toEntity()));
    }
    
     /**
     * DELETE 
     * @param documentoid corresponde al id del prestador
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la tarjeta a borrar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{documento: \\d+}")
    public void deletePrestador(@PathParam("documento") Long documentoid) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una tarjeta con id {0}", documentoid);
        PrestadorEntity entity =prestadorLogic.getServicio(documentoid);
        if (entity == null) {
            throw new WebApplicationException("El recurso /tarjetas/" + documentoid + " no existe.", 404);
        }
        prestadorLogic.deletePrestador(entity);

    }

     /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PrestadorEntity a una lista de
     * objetos PrestadorDetailDTO (json)
     *
     * @param entityList corresponde a la lista de prestadores de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de prestadores en forma DTO (json)
     */
    private List<PrestadorDetailDTO> listEntity2DetailDTO(List<PrestadorEntity> entityList) {
        List<PrestadorDetailDTO> list = new ArrayList<>();
        for ( PrestadorEntity entity : entityList) {
            list.add(new PrestadorDetailDTO(entity));
        }
        return list;
    }
}
