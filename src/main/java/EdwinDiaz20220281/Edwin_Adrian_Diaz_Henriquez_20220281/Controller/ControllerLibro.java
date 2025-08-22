package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Controller;

import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Models.DTO.DTOLibro;
import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Service.ServiceLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
