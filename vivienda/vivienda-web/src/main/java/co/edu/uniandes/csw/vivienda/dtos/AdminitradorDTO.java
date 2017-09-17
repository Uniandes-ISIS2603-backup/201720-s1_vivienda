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
public class AdminitradorDTO {
    
    private Long documento;
    private String nombre;
    private String username;
    private String password;
    
    public AdminitradorDTO() {
    }
    
    public AdminitradorDTO(AdministradorEntity admin) {
        this.documento = admin.getDocumento();
        this.nombre = admin.getNombre();
        this.username = admin.getUsername();
        this.password = admin.getPassword();
    }

    /**
     * @return the documento
     */
    public Long getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public AdministradorEntity toEntity() {
        AdministradorEntity entity = new AdministradorEntity();
        entity.setDocumento(this.documento);
        entity.setNombre(this.nombre);
        entity.setPassword(this.password);
        entity.setUsername(this.username);
        return entity;
    }
}
