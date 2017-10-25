/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.ServicioEntity;

/**
 *
 * @author rj.gonzalez10
 */
public class ServicioDetailDTO extends ServicioDTO{
    
    private PrestadorDTO prestador;
    
    public ServicioDetailDTO() {
    }
    
    public ServicioDetailDTO(ServicioEntity entity) {
        super(entity);
        if(entity != null)
        {
            if(entity.getMyPrestador() != null)
            {

                prestador = new PrestadorDTO(entity.getMyPrestador());
            } 
        }

    }
        
    

    /**
     * @return the admin
     */
    public PrestadorDTO getPrestador() {
        return prestador;
    }

    /**
     * @param prest the admin to set
     */
    public void setAdmin(PrestadorDTO prest) {
        this.prestador = prest;
    }
    
    
    @Override
    public ServicioEntity toEntity() {
        ServicioEntity servicioE = super.toEntity();
        if(this.prestador != null)
        {

            servicioE.setMyPrestador(this.prestador.toEntity());

        }
        return servicioE;
    }
}
