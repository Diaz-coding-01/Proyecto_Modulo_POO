package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Models.DTO;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//Getters y setters para el service
@Getter @Setter
public class DTOAutor {
    private Long id;

    @NotBlank //Indica que el campo no puede recibir espacios en blanco ni vacios
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 a 100 carácteres") //Agregamos un mínimo de 2 y un máximo de 100 carácteres
    private String nombre;

    @NotBlank @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 a 100 carácteres") //
    private String apellido;

    @Nullable //Indica que el campo es NULL - Sirve para legibilidad
    @Size(max = 50, message = "La nacionalidad tiene un máximo de 50 carácteres")
    private String nacionalidad;

    @Nullable
    @Past(message = "La fecha de nacimiento debe ser en el pasado") //Indicamos que la fecha de nacimiento que se debe registrar sea en el pasado
    private LocalDate fecha_nacimiento; //Hago uso de LocalDate porque para Spring es más fácil manejarlo
}
