package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Service;

import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Entity.EntityLibro;
import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Models.DTO.DTOAutor;
import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Models.DTO.DTOLibro;
import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Repository.RepositoryLibro;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceLibro {
    @Autowired
    private RepositoryLibro objRepoLibros;

    public List<DTOLibro> obtenerLibros() {
        List<EntityLibro> libros = objRepoLibros.findAll();
        return libros.stream().map(this::convertirADTOLibros).collect(Collectors.toList());
    }

    private DTOLibro convertirADTOLibros(EntityLibro entityLibros){
        DTOLibro objDTOLibros = new DTOLibro();
        objDTOLibros.setId(entityLibros.getId());
        objDTOLibros.setTitulo(entityLibros.getTitulo());
        objDTOLibros.setIsbn(entityLibros.getIsbn());
        objDTOLibros.setAño_publicacion(entityLibros.getAño_publicacion());
        objDTOLibros.setGenero(entityLibros.getGenero());
        objDTOLibros.setAutor_id(entityLibros.getAutor_id());

        return objDTOLibros;
    }

    private EntityLibro convertirAEntityLibros(DTOLibro dtoLibro){
        EntityLibro objEntityLibro = new EntityLibro();
        objEntityLibro.setTitulo(dtoLibro.getTitulo());
        objEntityLibro.setIsbn(dtoLibro.getIsbn());
        objEntityLibro.setAño_publicacion(dtoLibro.getAño_publicacion());
        objEntityLibro.setGenero(dtoLibro.getGenero());
        objEntityLibro.setAutor_id(dtoLibro.getAutor_id());

        return objEntityLibro;
    }
}
