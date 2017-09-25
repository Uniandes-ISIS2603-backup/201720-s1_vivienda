/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.PisoEntity;
import co.edu.uniandes.csw.vivienda.entities.TorreEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author da.solano1
 */

public class TorreDetailDTO extends TorreDTO {
    private List<PisoDTO> pisos; 
    public TorreDetailDTO(){
        
    }
    public TorreDetailDTO(TorreEntity torre){
        super(torre); 
        ArrayList<PisoDTO> arreglo1 = new ArrayList<>();
        List<PisoEntity> pisostemp = torre.getPisos();
        for(PisoEntity tor : pisostemp)
        {
            arreglo1.add(new PisoDTO(tor));
        }
        setPisos(arreglo1);
        
    }

    public List<PisoDTO> getPisos() {
        return pisos;
    }

    public void setPisos(List<PisoDTO> pisos) {
        this.pisos = pisos;
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
