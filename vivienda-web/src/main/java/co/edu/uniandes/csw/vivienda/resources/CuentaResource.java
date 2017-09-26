/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;

/**
 *
 * @author mp.franco10
 */
import co.edu.uniandes.csw.vivienda.dtos.CuentaDetailDTO;
import co.edu.uniandes.csw.vivienda.ejb.CuentaLogic;
import co.edu.uniandes.csw.vivienda.entities.CuentaEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.vivienda.persistence.CuentaPersistence;
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
 * Clase que implementa el recurso REST correspondiente a "cuentas".
 *
 * @author mp.franco10
 *
 */
@Path("cuentas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CuentaResource {

    @Inject
    CuentaLogic cuentaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(CuentaPersistence.class.getName());

    /**
     * POST
     *
     * @param cuenta correponde a la representación java del objeto json enviado
     * en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java.
     * @throws BusinessLogicException
     */
    @POST
    public CuentaDetailDTO createCuenta(CuentaDetailDTO cuenta) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        CuentaEntity cuentaEntity = cuenta.toEntity();
        // Invoca la lógica para crear la cuenta nueva
        CuentaEntity nuevoEditorial = cuentaLogic.createCuenta(cuentaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new CuentaDetailDTO(nuevoEditorial);
    }

    /**
     * GET para todas las cuentas
     *
     * @return la lista de todas las cuentas en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<CuentaDetailDTO> getCuentas() throws BusinessLogicException {
        return listEntity2DetailDTO(cuentaLogic.getCuentas());
    }

    /**
     * GET para una cuenta
     *
     * @param id corresponde al id de la cuenta buscada.
     * @return La cuenta encontrada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la cuenta buscada se retorna un 404 con el
     * mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public CuentaDetailDTO getCuenta(@PathParam("id") Long id) throws BusinessLogicException {
        CuentaEntity entity = cuentaLogic.getCuenta(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /cuentas/" + id + " no existe.", 404);
        }
        return new CuentaDetailDTO(cuentaLogic.getCuenta(id));
    }

    /**
     * PUT
     *
     * @param id corresponde a la cuenta a actualizar.
     * @param cuenta corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La cuenta actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la cuenta a actualizar se retorna un 404
     * con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public CuentaDetailDTO updateCuenta(@PathParam("id") Long id, CuentaDetailDTO cuenta) throws BusinessLogicException {
        cuenta.setId(id);
        CuentaEntity entity = cuentaLogic.getCuenta(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /cuentas/" + id + " no existe.", 404);
        }
        return new CuentaDetailDTO(cuentaLogic.updateCuenta(id, cuenta.toEntity()));
    }

    /**
     * DELETE
     *
     * @param id corresponde a la cuenta a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la cuenta a actualizar se retorna un 404
     * con el mensaje.
     * @throws java.sql.SQLException
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCuenta(@PathParam("id") Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una cuenta con id {0}", id);
        CuentaEntity entity = cuentaLogic.getCuenta(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /cuentas/" + id + " no existe.", 404);
        }
        cuentaLogic.deleteCuenta(id);

    }

    @Path("{cuentaId: \\d+}/ordenPagos")
    public Class<CuentaOrdenPagoResource> getCuentaOrdenPagoResource(@PathParam("cuentaId") Long cuentasId) {
        CuentaEntity entity = cuentaLogic.getCuenta(cuentasId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /cuentas/" + cuentasId + " no existe.", 404);
        }
        return CuentaOrdenPagoResource.class;
    }

    /**
     *
     * lista de entidades a DTO.
     *
     *
     * @param entityList corresponde a la lista de cuentas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de cuentas en forma DTO (json)
     */
    private List<CuentaDetailDTO> listEntity2DetailDTO(List<CuentaEntity> entityList) {
        List<CuentaDetailDTO> list = new ArrayList<>();
        for (CuentaEntity entity : entityList) {
            list.add(new CuentaDetailDTO(entity));
        }
        return list;
    }
}
