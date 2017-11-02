package co.edu.uniandes.csw.vivienda.dtos;

import co.edu.uniandes.csw.vivienda.entities.EstudianteEntity;

/**
 *
 * @author e.reyesm
 */
public class EstudianteDTO {
    
    private Long documento;
    private String nombre;
    private String userName;
    private String passWord;
    
     /**
     * Constructor por defecto
     */
    public EstudianteDTO() {
        //Constructor vacío, se utiliza el otro
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param estudiante: Es la entidad que se va a convertir a DTO
     */
    public EstudianteDTO(EstudianteEntity estudiante) {
        
        this.documento = estudiante.getDocumento();
        this.nombre = estudiante.getNombre();
        this.userName = estudiante.getUserName();
        this.passWord = estudiante.getPassWord();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
    

    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public EstudianteEntity toEntity() {
        EstudianteEntity entity = new EstudianteEntity();
        entity.setDocumento(this.documento);
        entity.setNombre(this.nombre);
        entity.setUserName(this.userName);
        entity.setPassWord(this.passWord);
        return entity;
    }
    
    
}
