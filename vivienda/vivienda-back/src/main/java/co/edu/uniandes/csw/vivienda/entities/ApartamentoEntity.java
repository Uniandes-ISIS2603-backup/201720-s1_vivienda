/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.entities;

/**
 *
 * @author da.solano1
 */
public class ApartamentoEntity {
    private String categoria; 
    private boolean disponible; 
    private Integer numApartamento; 

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
    
}
