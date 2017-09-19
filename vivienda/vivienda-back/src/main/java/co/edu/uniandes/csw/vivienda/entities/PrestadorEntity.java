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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author rj.gonzalez10
 */
@Entity
public class PrestadorEntity implements Serializable {
    @PodamExclude
    @OneToMany(mappedBy= "myPrestador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServicioEntity> respuesta;
    
    private  String nombre;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  documento;
    private boolean disponible;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
     
    
  
    
}
