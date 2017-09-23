/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author mp.franco10
 */

@Entity
public class CuentaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int renta;

    @PodamExclude
    @OneToOne
    @JoinColumn(name = "cuenta")
    private EstudianteEntity estudiante;

    @PodamExclude
    @OneToMany
    private List<OrdenPagoEntity> ordenPagos;

    @PodamExclude
    @OneToMany(mappedBy = "cuenta")
    private List<TarjetaEntity> tarjeta;


    public EstudianteEntity getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteEntity estudiante) {
        this.estudiante = estudiante;
    }

    public List<OrdenPagoEntity> getOrdenPagos() {
        return ordenPagos;
    }

    public void setOrdenPagos(List<OrdenPagoEntity> ordenPagos) {
        this.ordenPagos = ordenPagos;
    }

    public List<TarjetaEntity> getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(List<TarjetaEntity> tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRenta() {
        return renta;
    }

    public void setRenta(int renta) {
        this.renta = renta;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getId() != null && ((CuentaEntity) obj).getId() != null) {
            return this.getId().equals(((CuentaEntity) obj).getId());
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
