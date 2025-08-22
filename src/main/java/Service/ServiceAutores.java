package Service;

import Entity.EntityAutores;
import Models.DTO.DTOAutores;
import Repository.RepositoryAutores;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceAutores {
    @Autowired
    private RepositoryAutores objRepoAutores;

    public List<DTOAutores> obtenerAutores() {
        List<EntityAutores> autores = objRepoAutores.findAll();
        return autores.stream().map(this::convertirADTOAutores).collect(Collectors.toList());
    }

    public DTOAutores registrarAutor(@Valid DTOAutores dtoAutores) {
        if (dtoAutores == null) { throw new IllegalArgumentException("Todos los campos son obligatorios"); }

        EntityAutores registrado = objRepoAutores.save(convertirAEntidadAutores(dtoAutores));
        return convertirADTOAutores(registrado);
    }

    private DTOAutores convertirADTOAutores(EntityAutores entidadAutores) {
        DTOAutores objDTOAutores = new DTOAutores();
        objDTOAutores.setId(entidadAutores.getId());
        objDTOAutores.setNombre(entidadAutores.getNombre());
        objDTOAutores.setApellido(entidadAutores.getApellido());
        objDTOAutores.setNacionalidad(entidadAutores.getNacionalidad());
        objDTOAutores.setFecha_nacimiento(entidadAutores.getFecha_nacimiento());

        return objDTOAutores;
    }

    private EntityAutores convertirAEntidadAutores(DTOAutores dtoAutores){
        EntityAutores objEntidadAutores = new EntityAutores();
        objEntidadAutores.setNombre(dtoAutores.getNombre());
        objEntidadAutores.setApellido(dtoAutores.getApellido());
        objEntidadAutores.setNacionalidad(dtoAutores.getNacionalidad());
        objEntidadAutores.setFecha_nacimiento(dtoAutores.getFecha_nacimiento());

        return objEntidadAutores;
    }
}
