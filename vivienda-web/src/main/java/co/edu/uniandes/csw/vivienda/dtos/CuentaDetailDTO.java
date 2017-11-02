
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

    private List<OrdenPagoDTO> ordenPagos;

    private List<TarjetaDTO> tarjeta;

    /**
     * Constructor por defecto
     */
    public CuentaDetailDTO() {
        //Constructor vacío, se utiliza el otro
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public CuentaDetailDTO(CuentaEntity entity) {
        super(entity);
        if(entity!=null){
            if(entity.getEstudiante()!=null)
            {
               this.estudiante = new EstudianteDTO(entity.getEstudiante()); 
            }
            if (entity.getTarjeta() != null) {
                tarjeta = new ArrayList<>();
                for (TarjetaEntity entityTarjeta : entity.getTarjeta()) {
                    tarjeta.add(new TarjetaDTO(entityTarjeta));
                }
            }
            if (entity.getOrdenPagos() != null) {
                ordenPagos = new ArrayList<>();
                for (OrdenPagoEntity entityOrdenPago : entity.getOrdenPagos()) {
                    ordenPagos.add(new OrdenPagoDTO(entityOrdenPago));
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
        if (this.tarjeta != null) {
            List<TarjetaEntity> tarjetasEntity = new ArrayList<>();
            for (TarjetaDTO dtoTarjeta : tarjeta) {
                tarjetasEntity.add(dtoTarjeta.toEntity());
            }
            cuentaE.setTarjeta(tarjetasEntity);
        }
        if (this.ordenPagos != null) {
            List<OrdenPagoEntity> ordenesEntity = new ArrayList<>();
            for (OrdenPagoDTO dtoOrdenPago : ordenPagos) {
                ordenesEntity.add(dtoOrdenPago.toEntity());
            }
            cuentaE.setOrdenPagos(ordenesEntity);
        }


        return cuentaE;
    }

    public EstudianteDTO getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteDTO estudiante) {
        this.estudiante = estudiante;
    }

    public List<OrdenPagoDTO> getOrdenPagos() {
        return ordenPagos;
    }

    public void setOrdenPagos(List<OrdenPagoDTO> ordenPagos) {
        this.ordenPagos = ordenPagos;
    }

    public List<TarjetaDTO> getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(List<TarjetaDTO> tarjeta) {
        this.tarjeta = tarjeta;
    }
}
