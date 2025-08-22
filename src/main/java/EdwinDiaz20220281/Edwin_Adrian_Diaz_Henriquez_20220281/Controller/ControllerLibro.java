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
@Validated
public class ControllerLibro {
    @Autowired
    private ServiceLibro objServiceLibros;

    //READ
    @GetMapping("/obtenerLibros")
    public List<DTOLibro> obtenerLibros(){
        return objServiceLibros.obtenerLibros();
    }

    @PostMapping("/registrarLibro")
    public ResponseEntity<?> registrarLibro(@Valid @RequestBody DTOLibro dtoLibro){
        try{
            DTOLibro registrado = objServiceLibros.registrarLibro(dtoLibro);
            if (registrado == null){
                return ResponseEntity.badRequest().body(Map.of(
                        "status: ", "No se pudo registrar el libro",
                        "message: ", "Datos inválidos"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "Exitoso",
                    "message", "Libro registrado",
                    "data", registrado
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status: ", "No se pudo registrar el libro",
                    "message: ", e.getMessage()
            ));
        }
    }

    @PutMapping("/actualizarLibro/{id}")
    public ResponseEntity<?> actualizarLibro(
            @Valid @RequestBody DTOLibro dtoLibro,
            @PathVariable Long id
    ){
        try{
            DTOLibro registrado = objServiceLibros.actualizarLibro(dtoLibro, id);
            if (registrado == null){
                return ResponseEntity.badRequest().body(Map.of(
                        "status: ", "No se pudo actualizar el libro",
                        "message: ", "Datos inválidos"
                ));
            }
            return ResponseEntity.ok("Libro actualizado");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status: ", "No se pudo actualizar el libro",
                    "message: ", e.getMessage()
            ));
        }
    }

    @PutMapping("/eliminarLibro/{id}")
    public ResponseEntity<?> eliminarLibro(@PathVariable Long id){
        try{
            if (!objServiceLibros.eliminarLibro(id)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        "status: ", "No se pudo eliminar el libro",
                        "message: ", "Datos inválidos"
                ));
            }
            return ResponseEntity.ok("Libro eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status: ", "No se pudo eliminar el libro",
                    "message: ", e.getMessage()
            ));
        }
    }
}
