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
 * @author mp.franco10
 */

@Entity
public class CuentaEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int renta;

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
