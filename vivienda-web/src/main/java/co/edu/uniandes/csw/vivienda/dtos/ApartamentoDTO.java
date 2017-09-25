/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.ApartamentoEntity;

/**
 *
 * @author da.solano1
 */
public class ApartamentoDTO {
    private String categoria; 
    private boolean disponible; 
    private Integer numApartamento;
    
    public ApartamentoDTO(){
        
    }

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
    public ApartamentoDTO(ApartamentoEntity apartamento){
       this.categoria = apartamento.getCategoria(); 
       this.disponible = apartamento.isDisponible(); 
       this.numApartamento = apartamento.getNumApartamento(); 
    }
    public ApartamentoEntity toEntity(){
        ApartamentoEntity entity = new ApartamentoEntity(); 
        entity.setCategoria(this.categoria);
        entity.setDisponible(this.disponible);
        entity.setNumApartamento(this.numApartamento);
        return entity; 
    }
         
}
