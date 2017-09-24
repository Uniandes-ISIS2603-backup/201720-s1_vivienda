/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.CuentaEntity;
import co.edu.uniandes.csw.vivienda.entities.OrdenPagoEntity;
import co.edu.uniandes.csw.vivienda.entities.TarjetaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mp.franco10
 */
public class CuentaDetailDTO extends CuentaDTO {

    private EstudianteDTO estudiante;

    private List<OrdenPagoDTO> ordenPagosPaid;

    private List<OrdenPagoDTO> ordenPagosNotPaid;

    private List<TarjetaDTO> tarjeta;

    /**
     * Constructor por defecto
     */
    public CuentaDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public CuentaDetailDTO(CuentaEntity entity) {
        super(entity);
        this.estudiante = new EstudianteDTO(entity.getEstudiante());
        if (entity != null) {
            if (entity.getTarjeta() != null) {
                tarjeta = new ArrayList<>();
                for (TarjetaEntity entityTarjeta : entity.getTarjeta()) {
                    tarjeta.add(new TarjetaDTO(entityTarjeta));
                }
            }
            if (entity.getOrdenPagosPaid() != null) {
                ordenPagosPaid = new ArrayList<>();
                for (OrdenPagoEntity entityOrdenPago : entity.getOrdenPagosPaid()) {
                    ordenPagosPaid.add(new OrdenPagoDTO(entityOrdenPago));
                }
            }
            if (entity.getOrdenPagosNotPaid() != null) {
                ordenPagosNotPaid = new ArrayList<>();
                for (OrdenPagoEntity entityOrdenPago : entity.getOrdenPagosNotPaid()) {
                    ordenPagosNotPaid.add(new OrdenPagoDTO(entityOrdenPago));
                }
            }
        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return
     */
    @Override
    public CuentaEntity toEntity() {
        CuentaEntity cuentaE = super.toEntity();
        if (tarjeta != null) {
            List<TarjetaEntity> tarjetasEntity = new ArrayList<>();
            for (TarjetaDTO dtoTarjeta : tarjeta) {
                tarjetasEntity.add(dtoTarjeta.toEntity());
            }
            cuentaE.setTarjeta(tarjetasEntity);
        }
        if (ordenPagosPaid != null) {
            List<OrdenPagoEntity> ordenesEntity = new ArrayList<>();
            for (OrdenPagoDTO dtoOrdenPago : ordenPagosPaid) {
                ordenesEntity.add(dtoOrdenPago.toEntity());
            }
            cuentaE.setOrdenPagosPaid(ordenesEntity);
        }
        if (ordenPagosNotPaid != null) {
            List<OrdenPagoEntity> ordenesEntity = new ArrayList<>();
            for (OrdenPagoDTO dtoOrdenPago : ordenPagosNotPaid) {
                ordenesEntity.add(dtoOrdenPago.toEntity());
            }
            cuentaE.setOrdenPagosNotPaid(ordenesEntity);
        }

        return cuentaE;
    }

    public EstudianteDTO getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteDTO estudiante) {
        this.estudiante = estudiante;
    }

    public List<OrdenPagoDTO> getOrdenPagosPaid() {
        return ordenPagosPaid;
    }

    public void setOrdenPagosPaid(List<OrdenPagoDTO> ordenPagosPaid) {
        this.ordenPagosPaid = ordenPagosPaid;
    }

    public List<OrdenPagoDTO> getOrdenPagosNotPaid() {
        return ordenPagosNotPaid;
    }

    public void setOrdenPagosNotPaid(List<OrdenPagoDTO> ordenPagosNotPaid) {
        this.ordenPagosNotPaid = ordenPagosNotPaid;
    }

    public List<TarjetaDTO> getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(List<TarjetaDTO> tarjeta) {
        this.tarjeta = tarjeta;
    }
}
