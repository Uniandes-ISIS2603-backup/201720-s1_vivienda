/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.AdministradorEntity;

/**
 *
 * @author da.ramirezv
 */
public class AdministradorDTO {
    
    /**
     * variables de clase
     */
    private Long documento;
    private String nombre;
    private String username;
    private String password;
    
    /**
     * constructor que no se usa
     */
    public AdministradorDTO() {
        //Constructor vacío, se utiliza el otro 
    }
    
    /**
     * constructor que se va a utilizar
     * @param admin 
     */
    public AdministradorDTO(AdministradorEntity admin) {
        this.documento = admin.getDocumento();
        this.nombre = admin.getNombre();
        this.username = admin.getUsername();
        this.password = admin.getPassword();
    }

    /**
     * pide el documento actual
     * @return the documento
     */
    public Long getDocumento() {
        return documento;
    }

    /**
     * cambia el documento actual
     * @param documento the documento to set
     */
    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    /**
     * pide el nombre actual
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * pone un nombre nuevo
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * pide el username actual
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * pone un username nuevo
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * devuelve la contraseña
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * pone una contraseña nueva
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Convierte un dto en administradorEntity
     * @return un objeto de tipo AdministradorEntity
     */
    public AdministradorEntity toEntity() {
        AdministradorEntity entity = new AdministradorEntity();
        entity.setDocumento(this.documento);
        entity.setNombre(this.nombre);
        entity.setPassword(this.password);
        entity.setUsername(this.username);
        return entity;
    }
}
