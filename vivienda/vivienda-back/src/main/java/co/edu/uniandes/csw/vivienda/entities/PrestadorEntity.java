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
 * @author da.ramirezv
 */
@Entity
public class PrestadorEntity implements Serializable {
    
    private boolean disponible;
    private String nombre;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documento;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setPrecio(boolean disponible) {
        this.disponible = disponible;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long numeroTarjeta) {
        this.documento = numeroTarjeta;
    }
    
}
