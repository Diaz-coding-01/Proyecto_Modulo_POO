package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "AUTORES")
@Getter @Setter @ToString @EqualsAndHashCode
public class EntityAutor {
    @Id @Column(name = "ID",insertable = false , updatable = false)
    @SequenceGenerator(name = "seq_autor", sequenceName = "seq_autor", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_autor")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "APELLIDO", nullable = false, length = 100)
    private String apellido;

    @Column(name = "NACIONALIDAD", length = 50)
    private String nacionalidad;

    @Column(name = "FECHA_NACIMIENTO")
    private LocalDate fecha_nacimiento;
}
