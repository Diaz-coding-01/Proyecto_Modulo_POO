package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Controller;

import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Models.DTO.DTOAutor;
import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Service.ServiceAutor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/autor")
@Validated
public class ControllerAutor {
    @Autowired
    private ServiceAutor objServiceAutores;

    //READ
    @GetMapping("/obtenerAutores")
    public List<DTOAutor> obtenerAutores(){
        return objServiceAutores.obtenerAutores();
    }

    @PostMapping("/registrarAutor")
    public ResponseEntity<?> registrarAutor(@Valid @RequestBody DTOAutor dtoAutores){
        try{
            DTOAutor registrado = objServiceAutores.registrarAutor(dtoAutores);
            if (registrado == null){
                return ResponseEntity.internalServerError().body(Map.of(
                        "status: ", "No se pudo registrar el autor",
                        "message: ", "Datos inválidos"
                ));
            }
            return ResponseEntity.ok(Map.of(
                    "status", "Exitoso",
                    "message", "Autor registrado",
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
