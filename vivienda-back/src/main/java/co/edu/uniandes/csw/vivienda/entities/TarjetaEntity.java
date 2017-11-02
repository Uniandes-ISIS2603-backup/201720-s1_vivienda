/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author mp.franco10
 */
@Entity
public class TarjetaEntity implements Serializable {

    private String nombre;
    @Id
    private Long numeroTarjeta;

    @PodamExclude
    @ManyToOne(cascade=CascadeType.PERSIST)
    private CuentaEntity cuenta;

    public CuentaEntity getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaEntity cuenta) {
        this.cuenta = cuenta;
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

    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if(this.getClass()!=obj.getClass())
            return false;
        if (this.getNumeroTarjeta() != null && ((TarjetaEntity) obj).getNumeroTarjeta() != null) {
            return this.getNumeroTarjeta().equals(((TarjetaEntity) obj).getNumeroTarjeta());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if (this.getNumeroTarjeta() != null) {
            return this.getNumeroTarjeta().hashCode();
        }
        return super.hashCode();
    }
}
