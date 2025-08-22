package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

//Se indica que esta es una entidad
@Entity
@Table(name = "AUTORES") //Nombre de la tabla que es esta entidad
@Getter @Setter @ToString @EqualsAndHashCode
public class EntityAutor {
    @Id @Column(name = "ID",insertable = false , updatable = false)
    //Anotaciones para indicar que se usa una secuencia como manera que se genera el ID, como este no es insertado  se crea el ID con una secuencia
    @SequenceGenerator(name = "seq_autor", sequenceName = "seq_autor", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_autor") //Con el nombre de la secuencia como: "seq_autor"
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100) //Nombre de la tabla, NOT NULL y longitud de carácteres
    private String nombre;

    @Column(name = "APELLIDO", nullable = false, length = 100)
    private String apellido;

    @Column(name = "NACIONALIDAD", length = 50)
    private String nacionalidad;

    @Column(name = "FECHA_NACIMIENTO")
    private LocalDate fecha_nacimiento; //Uso de LocalDate porque facilita el manejo de fechas en Spring
}
