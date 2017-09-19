/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.AdministradorEntity;
import co.edu.uniandes.csw.vivienda.entities.TorreEntity;
import co.edu.uniandes.csw.vivienda.entities.SugerenciaEntity;
import co.edu.uniandes.csw.vivienda.entities.MensajeEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author da.ramirezv
 */
public class AdministradorDetailDTO extends AdministradorDTO{
    
    private List<MensajeDTO> mensajes;
   
    private List<SugerenciaDTO> sugerencias;
    
    private List<TorreDTO> torres;
    
    public AdministradorDetailDTO() {
    }
    
    public AdministradorDetailDTO(AdministradorEntity entity) {
        super(entity);
        //mensajes
        ArrayList<MensajeDTO> arreglo1 = new ArrayList<>();
        List<MensajeEntity> mensajestemp = entity.getMensajes();
        for(MensajeEntity men: mensajestemp)
        {
            arreglo1.add(new MensajeDTO(men));
        }
        setMensajes(arreglo1);
        
        //sugerencias
        ArrayList<SugerenciaDTO> arreglo2 = new ArrayList<>();
        List<SugerenciaEntity> sugerenciatemp = entity.getSugerencias();
        for(SugerenciaEntity sug: sugerenciatemp)
        {
            arreglo2.add(new SugerenciaDTO(sug));
        }
        setSugerencias(arreglo2);
        
        //torres
        ArrayList<TorreDTO> arreglo3 = new ArrayList<>();
        List<TorreEntity> torretemp = entity.getTorres();
        for(TorreEntity tor: torretemp)
        {
            arreglo3.add(new TorreDTO(tor));
        }
        setTorres(arreglo3);
    }
    
    public void setTorres(List<TorreDTO> ptorres)
    {
        torres = ptorres;
    }
    
    public List<TorreDTO> getTorres()
    {
        return torres;
    }
    
    public void setSugerencias(List<SugerenciaDTO> psugerencias)
    {
        sugerencias = psugerencias;
    }
    
    public List<SugerenciaDTO> getSugerencias()
    {
        return sugerencias;
    }
    
    public void setMensajes(List<MensajeDTO> pmensajes)
    {
        mensajes = pmensajes;
    }
    
    public List<MensajeDTO> getMensajes()
    {
        return mensajes;
    }
    
    @Override
    public AdministradorEntity toEntity() {
        AdministradorEntity adminE = super.toEntity();
        //mensajes
        List<MensajeEntity> mensajestemp = new ArrayList<>();
        for(MensajeDTO men: mensajes)
        {
            mensajestemp.add(men.toEntity());
        }
        adminE.setMensajes(mensajestemp);
        
        //sugerencias
        List<SugerenciaEntity> sugerenciastemp = new ArrayList<>();
        for(SugerenciaDTO sug: sugerencias)
        {
            sugerenciastemp.add(sug.toEntity());
        }
        adminE.setSugerencias(sugerenciastemp);
        
        //torres
        List<TorreEntity> torrestemp = new ArrayList<>();
        for(TorreDTO tor: torres)
        {
            torrestemp.add(tor.toEntity());
        }
        adminE.setTorres(torrestemp);
        
        return adminE;
    }
}
