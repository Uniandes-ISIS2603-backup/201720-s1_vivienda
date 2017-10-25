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
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author da.solano1
 */
@Entity
public class TorreEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id; 
    private boolean disponible;
    @PodamExclude
    @OneToMany(mappedBy = "torre",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PisoEntity> pisos; 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean Disponible) {
        this.disponible = Disponible;
    }

    public List<PisoEntity> getPisos() {
        return pisos;
    }

    public void setPisos(List<PisoEntity> pisos) {
        this.pisos = pisos;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj ==null)
            return false;
        if(this.getClass()!=obj.getClass())
            return false;
        if (this.getId() != null && ((TorreEntity) obj).getId() != null) {
            return this.getId().equals(((TorreEntity) obj).getId());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
    
}

