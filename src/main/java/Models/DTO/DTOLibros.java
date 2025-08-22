package Models.DTO;

import Entity.EntityAutores;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DTOLibros {
    private Long id;
    private String titulo;
    private String isbn;
    private Integer año_publicacion;
    private String genero;
    private EntityAutores autor_id;
}
