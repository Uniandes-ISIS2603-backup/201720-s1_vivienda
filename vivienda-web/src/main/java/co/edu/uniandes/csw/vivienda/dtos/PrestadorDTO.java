package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.PrestadorEntity;

/**
 *
 * @author rj.gonzalez10
 */
public class PrestadorDTO {

    private String nombre;
    private long documento;
    private boolean disponible;

    /**
     * Constructor por defecto
     */
    public PrestadorDTO() {
        //Constructor vacío, utilizar el otro
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param prestador: Es la entidad que se va a convertir a DTO
     */
    public PrestadorDTO(PrestadorEntity prestador) {

        this.documento = prestador.getDocumento();
        this.nombre = prestador.getNombre();
        this.disponible = prestador.isDisponible();

    }
    // método que retorna el nombre del prestador

    public String getNombre() {
        return nombre;
    }
//Método que permite asignar un nuevo nombre al prestador

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public PrestadorEntity toEntity() {
        PrestadorEntity entity = new PrestadorEntity();
        entity.setDisponible(this.disponible);
        entity.setDocumento(this.documento);
        entity.setNombre(this.nombre);
        return entity;
    }

}
