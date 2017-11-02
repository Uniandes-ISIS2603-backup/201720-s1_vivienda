
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.MensajeEntity;

/**
 *
 * @author da.ramirezv
 */
public class MensajeDTO {
    
    private Long id ;
    private String titulo;
    private String asunto;
    private String mensaje;
    
    public MensajeDTO() {
        //Constructor vacíom utilizar el otro
    }
    
    public MensajeDTO(MensajeEntity mensaje) {
        this.id = mensaje.getId();
        this.titulo = mensaje.getTitulo();
        this.asunto = mensaje.getAsunto();
        this.mensaje = mensaje.getMensaje();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * @param asunto the asunto to set
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public MensajeEntity toEntity() {
        MensajeEntity entity = new MensajeEntity();
        entity.setAsunto(this.asunto);
        entity.setId(this.id);
        entity.setMensaje(this.mensaje);
        entity.setTitulo(this.titulo);
        return entity;
    }
}
