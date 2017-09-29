/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author e.reyesm
 */
@Entity
public class OrdenPagoEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;
    
    private double precio;
    private Boolean pagada;

    @PodamExclude
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
    private ServicioEntity servicio;
    
    @PodamExclude
    @ManyToOne(cascade=CascadeType.PERSIST)
    private CuentaEntity cuenta;
    
   
    public CuentaEntity getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaEntity cuenta) {
        this.cuenta = cuenta;}
    
    
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

    public Boolean isPagada() {
        return pagada;
    }

    public void setPagada(Boolean pagada) {
        this.pagada = pagada;
    }
}
