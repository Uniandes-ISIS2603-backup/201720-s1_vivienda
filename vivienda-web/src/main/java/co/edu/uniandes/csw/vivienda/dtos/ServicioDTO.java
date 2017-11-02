/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.ServicioEntity;

/**
 *   
 * ServicioDTO Objeto de transferencia de datos de Servicio. Los DTO
 * contienen las represnetaciones de los JSON que se transfieren entre el cliente y el servidor.
 * @author rj.gonzalez10
 */
public class ServicioDTO {
    private Double precio;
    private String nombre;
    
    public ServicioDTO() {
        //Constructor vacío, utilizar el otro
    }
    public ServicioDTO(ServicioEntity servicio) {
        this.nombre = servicio.getNombre();
         this.precio = servicio.getPrecio();
        
    }
    public ServicioEntity toEntity(){
             ServicioEntity entity = new ServicioEntity();
        entity.setNombre(this.nombre);
        entity.setPrecio(this.precio);
        return entity;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



}
