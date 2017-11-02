/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.TarjetaEntity;

/**
 * TarjetaDTO Objeto de transferencia de datos de Editoriales. Los DTO contienen
 * las represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * @author mp.franco10
 */
public class TarjetaDTO {

    private String nombre;
    private Long numeroTarjeta;

    /**
     * Constructor por defecto
     */
    public TarjetaDTO() {
         //Constructor vacío, no usar este
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param tarjeta: Es la entidad que se va a convertir a DTO
     */
    public TarjetaDTO(TarjetaEntity tarjeta) {
        this.nombre = tarjeta.getNombre();
        this.numeroTarjeta = tarjeta.getNumeroTarjeta();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(Long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public TarjetaEntity toEntity() {
        TarjetaEntity entity = new TarjetaEntity();
        entity.setNombre(this.nombre);
        entity.setNumeroTarjeta(this.numeroTarjeta);
        return entity;
    }

}
