/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.TorreEntity;

/**
 *
 * @author da.solano1
 */

public class TorreDetailDTO extends TorreDTO {
    public TorreDetailDTO(){
        
    }
    public TorreDetailDTO(TorreEntity torre){
        super(torre); 
    }

    /**
     *
     * @return
     */
    @Override
    public TorreEntity toEntity(){
        TorreEntity TorreE = super.toEntity(); 
        return TorreE; 
    }
}
