/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author rj.gonzalez10
 */
@Entity
public class ServicioEntity implements Serializable {
    @PodamExclude

    @ManyToOne( fetch = FetchType.EAGER)

    private PrestadorEntity myPrestador;
    @Id
    private String nombre;
    private Double precio;

    public void setMyPrestador(PrestadorEntity myPrestador) {
        this.myPrestador = myPrestador;
    }

    public PrestadorEntity getMyPrestador() {
        return myPrestador;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    /**    @Override
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
    **/
    
}
