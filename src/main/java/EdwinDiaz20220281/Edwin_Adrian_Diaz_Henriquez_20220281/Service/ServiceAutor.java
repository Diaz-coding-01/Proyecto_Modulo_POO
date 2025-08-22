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

@Service
@Transactional
public class ServiceAutor {
    @Autowired
    private RepositoryAutor objRepoAutores;

    public List<DTOAutor> obtenerAutores() {
        List<EntityAutor> autores = objRepoAutores.findAll();
        return autores.stream().map(this::convertirADTOAutores).collect(Collectors.toList());
    }

    public DTOAutor registrarAutor(@Valid DTOAutor dtoAutores) {
        if (dtoAutores == null) { throw new IllegalArgumentException("Todos los campos son obligatorios"); }

        EntityAutor registrado = objRepoAutores.save(convertirAEntidadAutores(dtoAutores));
        return convertirADTOAutores(registrado);
    }

    private DTOAutor convertirADTOAutores(EntityAutor entidadAutores) {
        DTOAutor objDTOAutores = new DTOAutor();
        objDTOAutores.setId(entidadAutores.getId());
        objDTOAutores.setNombre(entidadAutores.getNombre());
        objDTOAutores.setApellido(entidadAutores.getApellido());
        objDTOAutores.setNacionalidad(entidadAutores.getNacionalidad());
        objDTOAutores.setFecha_nacimiento(entidadAutores.getFecha_nacimiento());

        return objDTOAutores;
    }

    private EntityAutor convertirAEntidadAutores(DTOAutor dtoAutores){
        EntityAutor objEntidadAutores = new EntityAutor();
        objEntidadAutores.setNombre(dtoAutores.getNombre());
        objEntidadAutores.setApellido(dtoAutores.getApellido());
        objEntidadAutores.setNacionalidad(dtoAutores.getNacionalidad());
        objEntidadAutores.setFecha_nacimiento(dtoAutores.getFecha_nacimiento());

        return objEntidadAutores;
    }
}
