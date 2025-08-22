package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Service;

import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Entity.EntityAutor;
import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Models.DTO.DTOAutor;
import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Repository.RepositoryAutor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //Indicamos que es un service
@Transactional //Buena práctica que permite flexibilidad en el service
public class ServiceAutor {
    //Inyección del repositorio
    @Autowired
    private RepositoryAutor objRepoAutores;

    //Método para obtener TODOS los autores
    public List<DTOAutor> obtenerAutores() {
        List<EntityAutor> autores = objRepoAutores.findAll(); //Mandamos a llamar al método del repositorio que busca TODOS los registros
        return autores.stream().map(this::convertirADTOAutores).collect(Collectors.toList()); //Convertimos la lista de entidades encontradas a Lista de DTO para mostrar al cliente
    }

    //Método solo para registrar un autor
    public DTOAutor registrarAutor(@Valid DTOAutor dtoAutores) { //Pide un JSON como parámetro
        if (dtoAutores == null) { throw new IllegalArgumentException("Todos los campos son obligatorios"); } //Si se ingresa un JSON vacío habrá error

        EntityAutor registrado = objRepoAutores.save(convertirAEntityAutores(dtoAutores)); //Se guarda el JSON al ser convertido a entidad para ser guardado con el método SAVE del repositorio
        return convertirADTOAutores(registrado); //Retornamos la entidad convertida a DTO para mostrar al cliente la confirmación que sus datos fueron guardados
    }

    //Método para converir de DTO a Entidad
    private DTOAutor convertirADTOAutores(EntityAutor entidadAutores) {
        DTOAutor objDTOAutores = new DTOAutor();
        objDTOAutores.setId(entidadAutores.getId());
        objDTOAutores.setNombre(entidadAutores.getNombre());
        objDTOAutores.setApellido(entidadAutores.getApellido());
        objDTOAutores.setNacionalidad(entidadAutores.getNacionalidad());
        objDTOAutores.setFecha_nacimiento(entidadAutores.getFecha_nacimiento());

        return objDTOAutores;
    }

    //Método para converir de Entidad a DTO
    private EntityAutor convertirAEntityAutores(DTOAutor dtoAutores){
        EntityAutor objEntidadAutores = new EntityAutor();
        objEntidadAutores.setNombre(dtoAutores.getNombre());
        objEntidadAutores.setApellido(dtoAutores.getApellido());
        objEntidadAutores.setNacionalidad(dtoAutores.getNacionalidad());
        objEntidadAutores.setFecha_nacimiento(dtoAutores.getFecha_nacimiento());

        return objEntidadAutores;
    }
}
