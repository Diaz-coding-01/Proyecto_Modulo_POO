package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Models.DTO;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class DTOAutor {
    private Long id;

    @NotBlank
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 a 100 carácteres")
    private String nombre;

    @NotBlank @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 a 100 carácteres")
    private String apellido;

    @Nullable
    @Size(max = 50, message = "La nacionalidad tiene un máximo de 50 carácteres")
    private String nacionalidad;

    @Nullable
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fecha_nacimiento;
}
