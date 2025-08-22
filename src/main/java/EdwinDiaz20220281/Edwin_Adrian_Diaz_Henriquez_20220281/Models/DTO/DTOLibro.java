package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Models.DTO;

import EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Entity.EntityAutor;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DTOLibro {
    private Long id;

    @NotBlank @Size(min = 2, max = 200, message = "El título debe tener entre 2 a 200 carácteres")
    private String titulo;

    @NotBlank @Size(max = 20, message = "El isbn debe tener un máximo de 20 carácteres")
    private String isbn;

    @Nullable
    private Integer año_publicacion;

    @Nullable @Size(max = 50, message = "El género debe tener un máximo de 50 carácteres")
    private String genero;

    @NotNull(message = "El autor es obligatorio") //Verificamos que se ingrese la FK
    private EntityAutor autor_id;
}
