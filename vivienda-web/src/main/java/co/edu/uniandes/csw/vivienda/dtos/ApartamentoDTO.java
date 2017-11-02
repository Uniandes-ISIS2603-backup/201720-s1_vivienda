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

    public ApartamentoDTO() {
        //Constructor vacío, se utiliza el otro 
    }

    public ApartamentoDTO(ApartamentoEntity apartamento) {
        this.categoria = apartamento.getCategoria();
        this.disponible = apartamento.isDisponible();
        this.numApartamento = apartamento.getNumApartamento();
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

    public ApartamentoEntity toEntity() {
        ApartamentoEntity entity = new ApartamentoEntity();
        entity.setCategoria(this.categoria);
        entity.setDisponible(this.disponible);
        entity.setNumApartamento(this.numApartamento);
        return entity;
    }

}
