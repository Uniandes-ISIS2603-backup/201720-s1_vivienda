/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author da.ramirezv
 */

@Entity
public class MensajeEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String titulo;
    private String asunto;
    private String mensaje;
    @PodamExclude
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private AdministradorEntity admin;


    /**
     * pone un nuevo administrador
     * @param admin 
     */
    public void setAdministrador(AdministradorEntity admin)
    {
        this.admin = admin;
    }
    /**
     * pide el administrador
     * @return 
     */
    public AdministradorEntity getAdministrador()
    {
        return admin;
    }
    /**
     * cambia el id
     * @param id 
     */
    public void setId(Long id)
    {
        this.id = id;
    }
    /**
     * pide el id
     * @return 
     */
    public Long getId() {
        return id;
    }
    /**
     * pide el titulo
     * @return 
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * cambia el titulo
     * @param titulo 
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**
    * pide el asunto
    * @return 
    */
    public String getAsunto() {
        return asunto;
    }
    /**
     * pone un nuevo asunto
     * @param asunto 
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    /**
     * pide el mensaje
     * @return 
     */
    public String getMensaje() {
        return mensaje;
    }
    /**
     * pone el mensaje
     * @param mensaje 
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    /**
     * metodo equals
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if(obj ==null)
            return false;
        if(this.getClass() != obj.getClass())
            return false;
        if (this.getId()!= null && ((MensajeEntity) obj).getId() != null) 
        {
            return this.getId().equals(((MensajeEntity) obj).getId());
        }
        return super.equals(obj);
    }
    /**
     * metodo para hcer hashing
     * @return 
     */
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}
