package Entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "AUTORES")
@Getter @Setter @ToString @EqualsAndHashCode
public class EntityLibros {
    @Id @Column(name = "ID",insertable = false , updatable = false)
    @SequenceGenerator(name = "seq_libro", sequenceName = "seq_libro", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_libro")
    private Long id;

    @Column(name = "TITULO", nullable = false, length = 200)
    private String titulo;

    @Column(name = "ISBN", nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(name = "AÑO_PUBLICACION")
    private Integer año_publicacion;

    @Column(name = "GENERO", length = 50)
    private String genero;

    @ManyToOne @Column(name = "AUTOR_ID")
    private EntityAutores autor_id;
}