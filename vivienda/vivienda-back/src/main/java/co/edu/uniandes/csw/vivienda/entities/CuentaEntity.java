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
public class CuentaEntity implements Serializable{
    private int renta; 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documento;

    public int getRenta() {
        return renta;
    }

    public void setRenta(int renta) {
        this.renta = renta;
    }


    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long numeroTarjeta) {
        this.documento = numeroTarjeta;
    }
}
