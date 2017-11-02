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
public class ApartamentoDetailDTO extends ApartamentoDTO {
    
    
    public ApartamentoDetailDTO(){
        //Constructor vacío, se utiliza el otro 
        
    }
    public ApartamentoDetailDTO(ApartamentoEntity apartamento) {
        super(apartamento);
    }
    @Override
    public ApartamentoEntity toEntity(){
        return super.toEntity(); 
    } 
}
