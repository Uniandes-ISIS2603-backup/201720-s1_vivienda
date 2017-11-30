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
    /**
     * variables de clase
     */
    private List<MensajeDTO> mensajes;
   
    private List<SugerenciaDTO> sugerencias;
    
    private List<TorreDTO> torres;
    
    /**
     * contructor vacio que no se utiliza
     */
    public AdministradorDetailDTO() {
        //Constructor vacío, se utiliza el otro 
    }
    
    /**
     * constructor
     * @param entity 
     */
    public AdministradorDetailDTO(AdministradorEntity entity) {
        super(entity);
        //mensajes
        if(entity != null)
        {
            if(entity.getMensajes() != null)
            {
                ArrayList<MensajeDTO> arreglo1 = new ArrayList<>();
                List<MensajeEntity> mensajestemp = entity.getMensajes();
                for(MensajeEntity men: mensajestemp)
                {
                    arreglo1.add(new MensajeDTO(men));
                }
                setMensajes(arreglo1);
            }
            //sugerencias
            if(entity.getSugerencias() != null)
            {
                ArrayList<SugerenciaDTO> arreglo2 = new ArrayList<>();
                List<SugerenciaEntity> sugerenciatemp = entity.getSugerencias();
                for(SugerenciaEntity sug: sugerenciatemp)
                {
                    arreglo2.add(new SugerenciaDTO(sug));
                }
                setSugerencias(arreglo2);
            }
            //torres
            if(entity.getTorres() != null)
            {
                ArrayList<TorreDTO> arreglo3 = new ArrayList<>();
                List<TorreEntity> torretemp = entity.getTorres();
                for(TorreEntity tor: torretemp)
                {
                    arreglo3.add(new TorreDTO(tor));
                }
                setTorres(arreglo3);
            }
        }
    }
    
    /**
     * cambia las torres actuales
     * @param ptorres 
     */
    public void setTorres(List<TorreDTO> ptorres)
    {
        torres = ptorres;
    }
    
    /**
     * pide las torres
     * @return 
     */
    public List<TorreDTO> getTorres()
    {
        return torres;
    }
    
    /**
     * cambia las sugerencias actuales
     * @param psugerencias 
     */
    public void setSugerencias(List<SugerenciaDTO> psugerencias)
    {
        sugerencias = psugerencias;
    }
    
    /**
     * pide las sugerencias actuales
     * @return 
     */
    public List<SugerenciaDTO> getSugerencias()
    {
        return sugerencias;
    }
    
    /**
     * cambia los mensajes actuales
     * @param pmensajes 
     */
    public void setMensajes(List<MensajeDTO> pmensajes)
    {
        mensajes = pmensajes;
    }
    
    /**
     * pide los mensajes actuales
     * @return 
     */
    public List<MensajeDTO> getMensajes()
    {
        return mensajes;
    }
    
    /**
     * devuelve un administradorEntity
     * @return devuelve un administradorEntity
     */
    @Override
    public AdministradorEntity toEntity() {
        AdministradorEntity adminE = super.toEntity();
        //mensajes
        if(mensajes != null)
        {
            List<MensajeEntity> mensajestemp = new ArrayList<>();
            for(MensajeDTO men: mensajes)
            {
                mensajestemp.add(men.toEntity());
            }
            adminE.setMensajes(mensajestemp);
        }
        
        
        //sugerencias
        if(sugerencias != null)
        {
            List<SugerenciaEntity> sugerenciastemp = new ArrayList<>();
            for(SugerenciaDTO sug: sugerencias)
            {
                sugerenciastemp.add(sug.toEntity());
            }
            adminE.setSugerencias(sugerenciastemp);  
        }
        
        
        //torres
        if(torres != null)
        {
            List<TorreEntity> torrestemp = new ArrayList<>();
            for(TorreDTO tor: torres)
            {
            torrestemp.add(tor.toEntity());
            }
            adminE.setTorres(torrestemp);
         }
        
        return adminE;   
        }
    }

