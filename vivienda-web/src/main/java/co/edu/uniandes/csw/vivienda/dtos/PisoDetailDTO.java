/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.ApartamentoEntity;
import co.edu.uniandes.csw.vivienda.entities.PisoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author da.solano1
 */
 
public class PisoDetailDTO extends PisoDTO {
    private List<ApartamentoDTO> apartamentos;

    public PisoDetailDTO(){
        
    }
    public PisoDetailDTO(PisoEntity entity){
        super(entity);
        ArrayList<ApartamentoDTO> arreglo1 = new ArrayList<>();
        List<ApartamentoEntity> apartamentostemp = entity.getApartamentos();
        for(ApartamentoEntity apar: apartamentostemp)
        {
            arreglo1.add(new ApartamentoDTO(apar));
        }
        setApartamentos(arreglo1);
    }
    
    public PisoEntity toEntity(){
        PisoEntity piso = super.toEntity();
        List<ApartamentoEntity> apartamentostemp = new ArrayList<>();
        for(ApartamentoDTO apar: apartamentos)
        {
            apartamentostemp.add(apar.toEntity());
        }
        piso.setApartamentos(apartamentostemp);
        return piso; 
        
    }

    public List<ApartamentoDTO> getApartamentos() {
        return apartamentos;
    }

    public void setApartamentos(List<ApartamentoDTO> apartamentos) {
        this.apartamentos = apartamentos;
    }
    
}
