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
