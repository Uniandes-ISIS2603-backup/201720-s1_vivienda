/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;

import co.edu.uniandes.csw.vivienda.dtos.ApartamentoDTO;
import co.edu.uniandes.csw.vivienda.ejb.PisoLogic;
import co.edu.uniandes.csw.vivienda.entities.ApartamentoEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author da.solano1
 */
@Produces("application/json")
@Consumes("application/json")
@Provider
public class PisoApartamentoResource {
    /**
     * Lógica de piso
     */
    @Inject
    PisoLogic pisoLogic;
    
    /**
     * GET de un piso
     * @param idPiso a buscar
     * @return piso con id dado
     * @throws BusinessLogicException si no existe el piso
     */
    @GET
    public List<ApartamentoDTO> getPisoApartamento(@PathParam("id") Integer idPiso) throws BusinessLogicException{
        List<ApartamentoEntity> listPisos = pisoLogic.getApartamentos(idPiso);
        return listEntity2DetailDTO(listPisos);
    }
    
    /**
     * Lista entidades a detail DTO
     * @param entityList lista de entidades a convertir
     * @return  lista de detail DTO
     */
    private List<ApartamentoDTO> listEntity2DetailDTO(List<ApartamentoEntity> entityList) {
        List<ApartamentoDTO> list = new ArrayList<>();
        for (ApartamentoEntity entity : entityList) {
            list.add(new ApartamentoDTO(entity));
        }
        return list;
    }
    
    
}
