package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Controller;

import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Models.DTO.DTOAutor;
import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Models.DTO.DTOLibro;
import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Service.ServiceLibro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/libro")
@Validated //Anotación para validación
public class ControllerLibro {
    //Se inyecta el service de los libros
    @Autowired
    private ServiceLibro objServiceLibros;

    //Método de búsqueda por título
    @GetMapping("/obtenerLibro/{titulo}") //Se pide un título como variable
    public DTOLibro obtenerLibros(@PathVariable String titulo){ //Con pathvariable se obtiene el titulo del libro que se va a buscar
        return objServiceLibros.obtenerLibroPorTitulo(titulo);
    }

    //READ
    @GetMapping("/obtenerLibros") //Endpoint para obtener todos los libros
    public List<DTOLibro> obtenerLibros(){ return objServiceLibros.obtenerLibros(); }

    //CREATE
    @PostMapping("/registrarLibro")
    public ResponseEntity<?> registrarLibro(@Valid @RequestBody DTOLibro dtoLibro){ //@Valid sirve para que los json recibidos sean validados y requestbody indica que este método pide un JSON
        try{
            DTOLibro registrado = objServiceLibros.registrarLibro(dtoLibro); //Mandamos a llamar al método del service
            if (registrado == null){
                return ResponseEntity.badRequest().body(Map.of( //Si no se logró registrar el libro el objDTO creado va a devolver null y se retornará el siguiente mensaje
                        "status: ", "No se pudo registrar el libro",
                        "message: ", "Datos inválidos"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of( //Si todo funcionó correctamente va a mandar el mensaje de éxito
                    "status", "Exitoso",
                    "message", "Libro registrado",
                    "data", registrado
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(  //Si se obtiene un error manda el mensaje con el status 500
                    "status: ", "No se pudo registrar el libro",
                    "message: ", e.getMessage()
            ));
        }
    }

    //UPDATE
    @PutMapping("/actualizarLibro/{id}") //Para actualizar se pide el ID para saber que registro va a modificarse
    public ResponseEntity<?> actualizarLibro(
            @Valid @RequestBody DTOLibro dtoLibro,
            @PathVariable Long id //Se guarda con pathvariable
    ){
        try{
            DTOLibro registrado = objServiceLibros.actualizarLibro(dtoLibro, id); //Se manda a llamar el método del service con los 2 argumentos
            if (registrado == null){
                return ResponseEntity.badRequest().body(Map.of(
                        "status: ", "No se pudo actualizar el libro",
                        "message: ", "Datos inválidos"
                ));
            }
            return ResponseEntity.ok("Libro actualizado"); //Si se modificó manda OK (200) como respuesta
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of( //Si se obtiene un error manda el mensaje con código 500
                    "status: ", "No se pudo actualizar el libro",
                    "message: ", e.getMessage() //Con el mensaje de error incluido
            ));
        }
    }

    //DELETE
    @DeleteMapping("/eliminarLibro/{id}") //Para eliminar se requiere un ID para eliminar ese registro
    public ResponseEntity<?> eliminarLibro(@PathVariable Long id){ //Se guarda con pathvariable
        try{
            if (!objServiceLibros.eliminarLibro(id)){ //El método en el service es boleano por lo cual si no se obtiene true (única manera de conseguirlo es si todo salió bien) manda mensaje de error
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        "status: ", "No se pudo eliminar el libro",
                        "message: ", "Datos inválidos"
                ));
            }
            return ResponseEntity.ok("Libro eliminado correctamente"); //No debe devolver nada porque se eliminó el registro pero se muestra mensaje de confirmación con código 200
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status: ", "No se pudo eliminar el libro", //Caso contrario si algo no sucedió bien en la eliminación del libro se manda un mensaje con su error respectivo
                    "message: ", e.getMessage()
            ));
        }
    }
}
