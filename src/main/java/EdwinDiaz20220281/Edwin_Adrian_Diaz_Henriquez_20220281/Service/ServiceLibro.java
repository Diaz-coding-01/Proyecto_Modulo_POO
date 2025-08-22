package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Service;

import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Entity.EntityAutor;
import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Entity.EntityLibro;
import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Models.DTO.DTOAutor;
import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Models.DTO.DTOLibro;
import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Repository.RepositoryLibro;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    public DTOLibro registrarLibro(@Valid DTOLibro dtoLibro) {
        if (dtoLibro == null) { throw new IllegalArgumentException("Todos los campos son obligatorios"); }

        EntityLibro registrado = objRepoLibros.save(convertirAEntityLibros(dtoLibro));
        return convertirADTOLibros(registrado);
    }

    public DTOLibro actualizarLibro(@Valid DTOLibro dtoLibro, Long id){
        EntityLibro existeLibro = objRepoLibros.findById(id).orElseThrow(() -> new IllegalArgumentException("Libro no encontrado"));

        //No se actualiza el ID
        existeLibro.setTitulo(dtoLibro.getTitulo());
        existeLibro.setIsbn(dtoLibro.getIsbn());
        existeLibro.setAño_publicacion(dtoLibro.getAño_publicacion());
        existeLibro.setGenero(dtoLibro.getGenero());
        existeLibro.setAutor_id(dtoLibro.getAutor_id());

        EntityLibro registrarLibro = objRepoLibros.save(existeLibro);

        return convertirADTOLibros(registrarLibro);
    }

    public boolean eliminarLibro(Long id){
        EntityLibro existeLibro = objRepoLibros.findById(id).orElse(null);
        if(existeLibro == null){ return false; }

        objRepoLibros.deleteById(id);
        return true;
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
