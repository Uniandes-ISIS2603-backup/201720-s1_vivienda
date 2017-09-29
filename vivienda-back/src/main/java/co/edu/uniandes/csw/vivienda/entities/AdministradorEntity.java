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
    
    public List<TorreEntity> getTorres()
    {
        return torres;
    }
    
    public void setTorres(List<TorreEntity> torres)
    {
        this.torres = torres;
    }
    
    public List<SugerenciaEntity> getSugerencias()
    {
        return sugerencias;
    }
    
    public void setSugerencias(List<SugerenciaEntity> sugerencias)
    {
        this.sugerencias = sugerencias;
    }
    
    public List<MensajeEntity> getMensajes()
    {
        return mensajes;
    }
    
    public void setMensajes(List<MensajeEntity> mensajes)
    {
        this.mensajes = mensajes;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    @Override
    public int hashCode() {
        if (this.getDocumento() != null) {
            return this.getDocumento().hashCode();
        }
        return super.hashCode();
    }
}
