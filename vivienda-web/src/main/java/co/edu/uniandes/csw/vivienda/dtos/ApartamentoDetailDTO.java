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
    PisoDTO piso; 
    public ApartamentoDetailDTO(){
        
    }
    public ApartamentoDetailDTO(ApartamentoEntity apartamento) {
        super(apartamento);
        this.setPiso(new PisoDTO(apartamento.getPiso()));
    }
    public ApartamentoEntity toEntity(){
        ApartamentoEntity entity = super.toEntity();
        entity.setPiso(piso.toEntity());
        return entity; 
        
    }

    public PisoDTO getPiso() {
        return piso;
    }

    public void setPiso(PisoDTO piso) {
        this.piso = piso;
    }
    
    
}
