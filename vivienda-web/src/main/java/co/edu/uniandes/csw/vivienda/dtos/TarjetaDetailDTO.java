/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.CuentaEntity;
import co.edu.uniandes.csw.vivienda.entities.TarjetaEntity;

/**
 *
 * @author mp.franco10
 */
public class TarjetaDetailDTO extends TarjetaDTO {

    private CuentaDTO cuenta;

    /**
     * Constructor por defecto
     */
    public TarjetaDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public TarjetaDetailDTO(TarjetaEntity entity) {
        super(entity);
        this.cuenta = new CuentaDTO(entity.getCuenta());
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return
     */
    @Override
    public TarjetaEntity toEntity() {
        TarjetaEntity entity = super.toEntity();
        entity.setCuenta(this.cuenta.toEntity());
        return entity;
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }

}
