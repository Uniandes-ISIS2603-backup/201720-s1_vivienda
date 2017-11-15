
package co.edu.uniandes.csw.vivienda.dtos;
import co.edu.uniandes.csw.vivienda.entities.EstudianteEntity;
import co.edu.uniandes.csw.vivienda.entities.SugerenciaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author e.reyesm
 */
public class EstudianteDetailDTO extends EstudianteDTO {

    private CuentaDTO cuenta;
    private List<SugerenciaDTO> sugerencias;
    
     /**
     * Constructor por defecto
     */
    public EstudianteDetailDTO() {
        //Método vacío, se utiliza el otro
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public EstudianteDetailDTO(EstudianteEntity entity) {
        super(entity);
        List<SugerenciaEntity> antigua = entity.getSugerencias();
        if (!antigua.isEmpty()) {
            sugerencias = new ArrayList<>();
            for (SugerenciaEntity cop : antigua) {
                SugerenciaDTO agregar = new SugerenciaDTO(cop);
                sugerencias.add(agregar);
            }
        }
        if (antigua.isEmpty()) {
            sugerencias = new ArrayList<>();
        }
        if (entity.getCuenta() != null) {
            System.out.print("Esta entrando !!!!!!!!!!!!!!!!!!!!!!");
            cuenta = new CuentaDTO(entity.getCuenta());
        }
        if(entity.getCuenta() == null)
        {
            cuenta = new CuentaDTO();
        }

    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }

    public void setSugerencias(List<SugerenciaDTO> sugerencias) {
        this.sugerencias = sugerencias;
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public List<SugerenciaDTO> getSugerencias() {
        return sugerencias;
    }

   
    
    
    
   

    public List<SugerenciaEntity> cambiarLista(List<SugerenciaDTO> lista) {
        List<SugerenciaEntity> nueva = new ArrayList<>();
        for (SugerenciaDTO cop : lista) {
            SugerenciaEntity agregar = cop.toEntity();
            nueva.add(agregar);
        }
        return nueva;
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return
     */
    @Override
    public EstudianteEntity toEntity() {
        EstudianteEntity estudianteE = super.toEntity();
      
            if (cuenta != null)
            {
            estudianteE.setCuenta(this.cuenta.toEntity());
            }
            if (sugerencias != null)
            {
            estudianteE.setSugerencias(cambiarLista(this.sugerencias));
            }
            
        
        return estudianteE;
    }

}
