/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.OrdenPagoEntity;
import co.edu.uniandes.csw.vivienda.entities.PagoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mp.franco10
 */
public class PagoDetailDTO extends PagoDTO {

    private CuentaDTO cuenta;

    private List<OrdenPagoDTO> ordenPagos;

    
     /**
     * Constructor por defecto
     */
    public PagoDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public PagoDetailDTO(PagoEntity entity) {
        super(entity);
        if (entity != null) {
            if (entity.getOrdenPagos() != null) {
                ordenPagos = new ArrayList<>();
                for (OrdenPagoEntity entityOrdenPago : entity.getOrdenPagos()) {
                    ordenPagos.add(new OrdenPagoDTO(entityOrdenPago));
                }
            }
        this.cuenta = new CuentaDTO(entity.getCuenta()); 
        }
    }
    
    public List<OrdenPagoDTO> getOrdenPagos() {
        return ordenPagos;
    }

    public void setOrdenPagos(List<OrdenPagoDTO> ordenPagos) {
        this.ordenPagos = ordenPagos;
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public PagoEntity toEntity() {
        PagoEntity pagoE = super.toEntity();
        if (ordenPagos != null) {
            List<OrdenPagoEntity> ordenPagoEntity = new ArrayList<>();
            for (OrdenPagoDTO dtoOrdenPago : ordenPagos) {
                ordenPagoEntity.add(dtoOrdenPago.toEntity());
            }
            pagoE.setOrdenPagos(ordenPagoEntity);
        }
        return pagoE;
    }
}
