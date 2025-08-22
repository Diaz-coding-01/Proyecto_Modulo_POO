package Service;

import Entity.EntityLibros;
import Models.DTO.DTOAutores;
import Models.DTO.DTOLibros;
import Repository.RepositoryLibros;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceLibros {
    @Autowired
    private RepositoryLibros objRepoLibros;

    public List<DTOLibros> obtenerLibros() {
        List<EntityLibros> libros = objRepoLibros.findAll();
        return libros.stream().map(this::convertirADTOLibros).collect(Collectors.toList());
    }

    private DTOLibros convertirADTOLibros(EntityLibros entityLibros){
        DTOLibros objDTOLibros = new DTOLibros();
        objDTOLibros.setId(entityLibros.getId());
        objDTOLibros.setTitulo(entityLibros.getTitulo());
        objDTOLibros.setIsbn(entityLibros.getIsbn());
        objDTOLibros.setAño_publicacion(entityLibros.getAño_publicacion());
        objDTOLibros.setGenero(entityLibros.getGenero());
        objDTOLibros.setAutor_id(entityLibros.getAutor_id());

        return objDTOLibros;
    }
}
