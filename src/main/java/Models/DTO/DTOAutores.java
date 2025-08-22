package Models.DTO;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class DTOAutores {
    private Long id;

    @NotBlank @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 a 100 carácteres")
    private String nombre;

    @NotBlank @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 a 100 carácteres")
    private String apellido;

    @Nullable
    @Size(max = 50, message = "La nacionalidad tiene un máximo de 50 carácteres")
    private String nacionalidad;

    @Nullable
    private LocalDate fecha_nacimiento;
}
