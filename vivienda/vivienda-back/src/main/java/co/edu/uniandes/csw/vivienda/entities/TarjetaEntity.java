/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author mp.franco10
 */
@Entity
public class TarjetaEntity implements Serializable {

    private String nombre;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroTarjeta;

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
