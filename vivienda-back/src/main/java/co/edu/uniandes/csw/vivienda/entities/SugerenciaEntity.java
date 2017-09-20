/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author e.reyesm
 */
@Entity
public class SugerenciaEntity {

    @Id
    private Long id;
    private String mensaje;
    @PodamExclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sugerencias")
    private EstudianteEntity estudiante;
    @PodamExclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "")
    private AdministradorEntity administrador;

    public AdministradorEntity getAdministrador() {
        return administrador;
    }

    public EstudianteEntity getEstudiante() {
        return estudiante;
    }

    public void setAdministrador(AdministradorEntity administrador) {
        this.administrador = administrador;
    }

    public void setEstudiante(EstudianteEntity estudiante) {
        this.estudiante = estudiante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
