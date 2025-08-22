package Controller;

import Models.DTO.DTOAutores;
import Service.ServiceAutores;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/autor")
@Validated
public class ControllerAutores {
    @Autowired
    private ServiceAutores objServiceAutores;

    //READ
    @GetMapping("/obtenerAutores")
    public List<DTOAutores> obtenerAutores(){
        return objServiceAutores.obtenerAutores();
    }

    @PostMapping("/registrarAutor")
    public ResponseEntity<?> registrarAutor(@Valid @RequestBody DTOAutores dtoAutores){
        try{
            DTOAutores registrado = objServiceAutores.registrarAutor(dtoAutores);
            if (registrado == null){
                return ResponseEntity.internalServerError().body(Map.of(
                        "status: ", "No se pudo registrar el autor",
                        "message: ", "Datos inválidos"
                ));
            }
            return ResponseEntity.ok(Map.of(
                    "status", "Exitoso",
                    "message", "Libro registrado",
                    "data", registrado
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status: ", "No se pudo registrar el autor",
                    "message: ", e.getMessage()
            ));
        }
    }
}