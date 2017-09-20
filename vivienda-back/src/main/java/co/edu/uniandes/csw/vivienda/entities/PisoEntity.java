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
 * @author da.solano1
 */
@Entity
public class PisoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    private boolean disponible; 
    @PodamExclude
    @ManyToOne
    private TorreEntity torre;
    @PodamExclude 
    @OneToMany(mappedBy = "piso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApartamentoEntity> pisos; 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public TorreEntity getTorre() {
        return torre;
    }

    public void setTorre(TorreEntity torre) {
        this.torre = torre;
    }

    public List<ApartamentoEntity> getPisos() {
        return pisos;
    }

    public void setPisos(List<ApartamentoEntity> pisos) {
        this.pisos = pisos;
    }

    
    
    
    @Override
    public boolean equals(Object obj) {
        if (this.getId() != null && ((PisoEntity) obj).getId() != null) {
            return this.getId().equals(((PisoEntity) obj).getId());
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
