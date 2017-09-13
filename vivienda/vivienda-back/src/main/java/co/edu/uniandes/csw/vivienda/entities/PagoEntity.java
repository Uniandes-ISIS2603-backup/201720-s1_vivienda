/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author da.ramirezv
 */
@Entity
public class PagoEntity implements Serializable{
    
    private double totalPagado; 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documento;

    public double getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }


    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long numeroTarjeta) {
        this.documento = numeroTarjeta;
    }
}
