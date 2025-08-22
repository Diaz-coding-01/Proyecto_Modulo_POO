package Controller;

import Models.DTO.DTOAutores;
import Models.DTO.DTOLibros;
import Service.ServiceLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/libro")
@Validated
public class ControllerLibros { //Aquí se debe hacer el crud
    @Autowired
    private ServiceLibros objServiceLibros;

    //READ
    @GetMapping("/getLibros")
    public List<DTOLibros> obtenerAutores(){
        return objServiceLibros.obtenerLibros();
    }
}
