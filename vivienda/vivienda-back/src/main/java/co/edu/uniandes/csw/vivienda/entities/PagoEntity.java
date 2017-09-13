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

public class PagoEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroPago;
	private double totalPagado;
	public Long getNumeroPago() {
		return numeroPago;
	}
	public void setNumeroPago(Long numeroPago) {
		this.numeroPago = numeroPago;
	}
	public double getTotalPagado() {
		return totalPagado;
	}
	public void setTotalPagado(double totalPagado) {
		this.totalPagado = totalPagado;
	}
        
        @Override
    public boolean equals(Object obj) {
        if (this.getNumeroPago() != null && ((PagoEntity) obj).getNumeroPago() != null) {
            return this.getNumeroPago().equals(((PagoEntity) obj).getNumeroPago());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if (this.getNumeroPago() != null) {
            return this.getNumeroPago().hashCode();
        }
        return super.hashCode();
    }
}
