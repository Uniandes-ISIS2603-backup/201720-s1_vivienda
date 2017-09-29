/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.OrdenPagoEntity;

/**
 *
 * @author e.reyesm
 */
public class OrdenPagoDTO {
    
    private Long idPago;
    private double precio;
    private Boolean pagada;

    public Boolean isPagada() {
        return pagada;
    }

    public void setPagada(Boolean pagada) {
        this.pagada = pagada;
    }

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
      /**
     * Constructor por defecto
     */
    public OrdenPagoDTO() {
    }
    
       /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param ordenPago: Es la entidad que se va a convertir a DTO
     */
    public OrdenPagoDTO(OrdenPagoEntity ordenPago) {
        if(ordenPago!=null){
        this.idPago = ordenPago.getIdPago();
        this.precio = ordenPago.getPrecio();
        this.pagada = ordenPago.isPagada();}
    }

    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public OrdenPagoEntity toEntity() {
        OrdenPagoEntity entity = new OrdenPagoEntity();
        entity.setIdPago(this.idPago);
        entity.setPrecio(this.precio);
        entity.setPagada(this.pagada);
        return entity;
    }
}
