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
public class ServicioDetailDTO extends ServicioDTO {

    private PrestadorDTO prestador;

    public ServicioDetailDTO() {
        //Constructor vacío, utilizar el otro
    }

    public ServicioDetailDTO(ServicioEntity entity) {
        super(entity);

        if ( entity != null && entity.getMyPrestador() != null ) {

            prestador = new PrestadorDTO(entity.getMyPrestador());
            System.err.println("entro !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

    }

    

    public PrestadorDTO getPrestador() {
        return prestador;
    }

    /**
     * @return the admin
     */
    public void setPrestador(PrestadorDTO prestador) {    
        this.prestador = prestador;
    }

    @Override
    public ServicioEntity toEntity() {
        ServicioEntity servicioE = super.toEntity();
        System.out.println("lllllllllllllllllllllllllllllllllllllllllllllllllll");
        System.out.println("prestador"+prestador);
        if (this.prestador != null) {

            servicioE.setMyPrestador(this.prestador.toEntity());

        }
        return servicioE;
    }
}
