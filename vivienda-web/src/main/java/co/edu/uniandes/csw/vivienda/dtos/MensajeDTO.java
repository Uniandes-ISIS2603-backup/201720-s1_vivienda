
package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.MensajeEntity;

/**
 *
 * @author da.ramirezv
 */
public class MensajeDTO {
    
    /**
     * variables de clase
     */
    private Long id ;
    private String titulo;
    private String asunto;
    private String mensaje;
    
    /**
     * constructor vacio
     */
    public MensajeDTO() {
        //Constructor vacíom utilizar el otro
    }
    
    /**
     * constructor
     * @param mensaje 
     */
    public MensajeDTO(MensajeEntity mensaje) {
        this.id = mensaje.getId();
        this.titulo = mensaje.getTitulo();
        this.asunto = mensaje.getAsunto();
        this.mensaje = mensaje.getMensaje();
    }

    /**
     * pide el id
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * cambia el id
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * pide el titulo
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * cambia el titulo
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * pone el asunto
     * @return the asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * cambia el asunto
     * @param asunto the asunto to set
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * pide el mensaje
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * cambia el mensaje
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    /**
     * devuelve un mensajeEntity
     * @return 
     */
    public MensajeEntity toEntity() {
        MensajeEntity entity = new MensajeEntity();
        entity.setAsunto(this.asunto);
        entity.setId(this.id);
        entity.setMensaje(this.mensaje);
        entity.setTitulo(this.titulo);
        return entity;
    }
}
