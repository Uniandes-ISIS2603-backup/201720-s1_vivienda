package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.PrestadorEntity;

/**
 *
 * @author rj.gonzalez10
 */
public class PrestadorDetailDTO extends PrestadorDTO {

    public PrestadorDetailDTO() {
        //Constructor vacío, utililizar el otro
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public PrestadorDetailDTO(PrestadorEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return
     */
    @Override
    public PrestadorEntity toEntity() {
        return super.toEntity();
    }
}
