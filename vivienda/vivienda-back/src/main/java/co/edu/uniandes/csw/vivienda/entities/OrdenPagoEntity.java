/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author e.reyesm
 */
@Entity
public class OrdenPagoEntity {

    @Id
    private Long idPago;
    private double precio;
    @PodamExclude
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sevicio_id")
    private ServicioEntity servicio;

    public ServicioEntity getServicio() {
        return servicio;
    }

    public void setServicio(ServicioEntity servicio) {
        this.servicio = servicio;
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

}
