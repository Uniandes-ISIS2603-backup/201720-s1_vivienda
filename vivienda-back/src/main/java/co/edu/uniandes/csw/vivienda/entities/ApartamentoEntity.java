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
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author da.solano1
 */
@Entity
public class ApartamentoEntity implements Serializable {
    private String categoria; 
    private boolean disponible; 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numApartamento;
    @PodamExclude 
    @ManyToOne()
    private PisoEntity piso; 

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Integer getNumApartamento() {
        return numApartamento;
    }

    public void setNumApartamento(Integer numApartamento) {
        this.numApartamento = numApartamento;
    }

    public PisoEntity getPiso() {
        return piso;
    }

    public void setPiso(PisoEntity piso) {
        this.piso = piso;
    }
    
    
}
