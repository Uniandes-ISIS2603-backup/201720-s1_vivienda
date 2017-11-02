/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.resources;

import co.edu.uniandes.csw.vivienda.dtos.PisoDTO;
import co.edu.uniandes.csw.vivienda.dtos.PisoDetailDTO;
import co.edu.uniandes.csw.vivienda.ejb.PisoLogic;
import co.edu.uniandes.csw.vivienda.ejb.TorreLogic;
import co.edu.uniandes.csw.vivienda.entities.PisoEntity;
import co.edu.uniandes.csw.vivienda.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author da.solano1
 */
@Produces("application/json")
@Consumes("application/json")
@Provider
public class TorrePisoResource {
    
    @Inject
    TorreLogic torreLogic;
    
    @GET
    public List<PisoDTO> getTorrePiso(@PathParam("id") Integer idTorre) throws BusinessLogicException{
        List<PisoEntity> listPisos = torreLogic.getPisos(idTorre);
        return listEntity2DetailDTO(listPisos);
    }
    
    private List<PisoDTO> listEntity2DetailDTO(List<PisoEntity> entityList) {
        List<PisoDTO> list = new ArrayList<>();
        for (PisoEntity entity : entityList) {
            list.add(new PisoDTO(entity));
        }
        return list;
    }
    
    
}
