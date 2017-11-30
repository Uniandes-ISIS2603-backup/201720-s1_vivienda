/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author da.ramirezv
 */
@Entity
public class AdministradorEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documento;
    private String nombre;
    private String userName;
    private String password;
    @PodamExclude
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MensajeEntity> mensajes;
    @PodamExclude
    @OneToMany(mappedBy = "administrador", fetch = FetchType.LAZY)
    private List<SugerenciaEntity> sugerencias;
    @PodamExclude
    @OneToMany()
    private List<TorreEntity> torres;
    
    /**
     * pide las torres
     * @return 
     */
    public List<TorreEntity> getTorres()
    {
        return torres;
    }
    /**
     * actualiza las torres
     * @param torres 
     */
    public void setTorres(List<TorreEntity> torres)
    {
        this.torres = torres;
    }
    /**
     * pide las sugerencias
     * @return 
     */
    public List<SugerenciaEntity> getSugerencias()
    {
        return sugerencias;
    }
    /**
     * devuelve las sugerencias
     * @param sugerencias 
     */
    public void setSugerencias(List<SugerenciaEntity> sugerencias)
    {
        this.sugerencias = sugerencias;
    }
    /**
     * pide los mensajes
     * @return 
     */
    public List<MensajeEntity> getMensajes()
    {
        return mensajes;
    }
    /**
     * actualiza los mensajes
     * @param mensajes 
     */
    public void setMensajes(List<MensajeEntity> mensajes)
    {
        this.mensajes = mensajes;
    }
    /**
     * pide el nombre
     * @return 
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * actualiza el nombre
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * pide el documento
     * @return 
     */
    public Long getDocumento() {
        return documento;
    }
    /**
     * actualiza el documento
     * @param documento 
     */
    public void setDocumento(Long documento) {
        this.documento = documento;
    }
    /**
     * pide el username
     * @return 
     */
    public String getUsername() {
        return userName;
    }
    /**
     * actualiza el username
     * @param userName 
     */
    public void setUsername(String userName) {
        this.userName = userName;
    }
    /**
     * pide la contraseña
     * @return 
     */
    public String getPassword() {
        return password;
    }
    /**
     * actualiza la contraseña
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * metodo equals
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if(this.getClass() != obj.getClass())
            return false;
        if (this.getDocumento()!= null && ((AdministradorEntity) obj).getDocumento() != null) {
            return this.getDocumento().equals(((AdministradorEntity) obj).getDocumento());
        }
        return super.equals(obj);
    }
    /**
     * metodo de hashcode
     * @return 
     */
    @Override
    public int hashCode() {
        if (this.getDocumento() != null) {
            return this.getDocumento().hashCode();
        }
        return super.hashCode();
    }
}
