/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author e.reyesm
 */
@Entity
public class EstudianteEntity implements Serializable {

    @Id
    private Long documento;
    private String nombre;
    private String userName;
    private String passWord;
    @PodamExclude
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cuenta_id")
    private CuentaEntity cuenta;
    @PodamExclude
    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
    private List<SugerenciaEntity> sugerencias;
    @PodamExclude
    @ManyToOne(fetch = FetchType.EAGER)
    private ApartamentoEntity apartamento;

    public ApartamentoEntity getApartamento() {
        return apartamento;
    }

    public CuentaEntity getCuenta() {
        return cuenta;
    }

    public List<SugerenciaEntity> getSugerencias() {
        return sugerencias;
    }

    public void setApartamento(ApartamentoEntity apartamento) {
        this.apartamento = apartamento;
    }

    public void setCuenta(CuentaEntity cuenta) {
        this.cuenta = cuenta;
    }

    public void setSugerencias(List<SugerenciaEntity> sugerencias) {
        this.sugerencias = sugerencias;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

}
