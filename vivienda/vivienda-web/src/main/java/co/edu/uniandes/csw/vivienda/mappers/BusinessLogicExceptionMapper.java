/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.mappers;

import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author da.solano1
 */
public class BusinessLogicExceptionMapper implements ExceptionMapper<BusinessLogicException>{
     /**
     * Generador de una respuesta a partir de una excepción
     *
     * @param exception excepión a convertir a una respuesta REST
     * @return
     */
    @Override
    public Response toResponse(BusinessLogicException exception) {
        // retorna una respuesta
        return Response.status(Response.Status.PRECONDITION_FAILED)
                .entity(getInitCause(exception).getLocalizedMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build(); //To change body of generated methods, choose Tools | Templates.
    }

    private Throwable getInitCause(Throwable e) {
        if (e.getCause() != null) {
            return getInitCause(e.getCause());
        } else {
            return e;
        }
    }

}
