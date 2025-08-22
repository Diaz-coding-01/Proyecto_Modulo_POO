package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Service;

import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Entity.EntityLibro;
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
    //Inyectamos el repositorio para usar los métodos de la JPA
    @Autowired
    private RepositoryLibro objRepoLibros;

    //Método de búsqueda por el campo "Titulo"
    public DTOLibro obtenerLibroPorTitulo(String titulo) { //Se pide el titulo
        EntityLibro libro = objRepoLibros.findByTitulo(titulo); //Se manda a llamar un método creado manualmente en el repositorio con el titulo como parámetro de búsqueda
        return convertirADTOLibros(libro); //El resultado se va a convertir a DTO para mostrarlo al cliente
    }

    //Método para obtener TODOS los libros de la tabla
    public List<DTOLibro> obtenerLibros() {
        List<EntityLibro> libros = objRepoLibros.findAll(); //Método del repositorio para obtener todos (En sql como: SELECT * FROM libros)
        return libros.stream().map(this::convertirADTOLibros).collect(Collectors.toList()); //La lista de entidades pasa a ser lista de DTOs
    }

    //Método para registrar un libro
    public DTOLibro registrarLibro(@Valid DTOLibro dtoLibro) {
        if (dtoLibro == null) { throw new IllegalArgumentException("Todos los campos son obligatorios"); } //Si se manda un JSON sin campos manda error

        EntityLibro registrado = objRepoLibros.save(convertirAEntityLibros(dtoLibro)); //Se guarda usando el método "save" del repositorio
        return convertirADTOLibros(registrado); //Convertimos a formato DTO para mostrarlo al cliente como confirmación de la creación
    }

    //Método para modificar un libro
    public DTOLibro actualizarLibro(@Valid DTOLibro dtoLibro, Long id){ //Pide como parámetros el JSON y el ID del registro que se quiere actualizar
        EntityLibro existeLibro = objRepoLibros.findById(id).orElseThrow(() -> new IllegalArgumentException("Libro no encontrado")); //Se verifica que la entidad que se quiere modificar exista, sino lanza un error

        //Actualizamos los campos con los valores del JSON recibido
        //No se actualiza el ID
        existeLibro.setTitulo(dtoLibro.getTitulo()); //De la entidad que queremos actualizar se guarda lo que está en el JSON con ese nombre, en este caso, el título
        existeLibro.setIsbn(dtoLibro.getIsbn());
        existeLibro.setAño_publicacion(dtoLibro.getAño_publicacion());
        existeLibro.setGenero(dtoLibro.getGenero());
        existeLibro.setAutor_id(dtoLibro.getAutor_id());

        //Se guardan los campos al modificar el libro - No se necesita hacer uso del método save porque utilizo Transactional

        return convertirADTOLibros(existeLibro); //Retorna en formato JSON el registro modificado
    }

    //Método para eliminar un libro
    public boolean eliminarLibro(Long id){ //Pide como parámetro el ID
        EntityLibro existeLibro = objRepoLibros.findById(id).orElse(null); //Verifica su existencia, si no lo encuentra con el método del repositorio guardará null en la variable
        if(existeLibro == null){ return false; } //Si obtenemos null vamos a devolver un false, mencionando que NO se eliminó el registro

        objRepoLibros.deleteById(id); //Se elimina el registro
        return true; //Y devolvemos true
    }

    //Método para converir de DTO a Entidad
    private DTOLibro convertirADTOLibros(EntityLibro entityLibros){ //Pide entidad a convertir
        DTOLibro objDTOLibros = new DTOLibro(); //Nuevo DTO para devolver
        objDTOLibros.setId(entityLibros.getId());
        objDTOLibros.setTitulo(entityLibros.getTitulo());
        objDTOLibros.setIsbn(entityLibros.getIsbn());
        objDTOLibros.setAño_publicacion(entityLibros.getAño_publicacion());
        objDTOLibros.setGenero(entityLibros.getGenero());
        objDTOLibros.setAutor_id(entityLibros.getAutor_id());

        return objDTOLibros; //DTO con valores de la entidad
    }

    //Método para converir de Entidad a DTO
    private EntityLibro convertirAEntityLibros(DTOLibro dtoLibro){ //Pide entidad a convertir
        EntityLibro objEntityLibro = new EntityLibro(); //Nueva entidad para devolver
        objEntityLibro.setTitulo(dtoLibro.getTitulo());
        objEntityLibro.setIsbn(dtoLibro.getIsbn());
        objEntityLibro.setAño_publicacion(dtoLibro.getAño_publicacion());
        objEntityLibro.setGenero(dtoLibro.getGenero());
        objEntityLibro.setAutor_id(dtoLibro.getAutor_id());

        return objEntityLibro; //Entidad con valores del DTO
    }
}
