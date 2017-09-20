/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.PagoEntity;

/**
 *
 * @author mp.franco10
 */
public class PagoDTO {
 
    private Long numeroPago;
    private double totalPagado;
    
    /**
     * Constructor por defecto
     */
    public PagoDTO() {
    }

    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param pago: Es la entidad que se va a convertir a DTO 
     */
    public PagoDTO(PagoEntity pago) {
        this.numeroPago = pago.getNumeroPago();
        this.totalPagado = pago.getTotalPagado();
    }


    public Long getNumeroPago() {
        return numeroPago;
    }

    public void setNumeroPago(Long numeroPago) {
        this.numeroPago = numeroPago;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }
    
    /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO 
     */
    public PagoEntity toEntity() {
        PagoEntity entity = new PagoEntity();
        entity.setNumeroPago(this.numeroPago);
        entity.setTotalPagado(this.totalPagado);
        return entity;
    }
    

}
