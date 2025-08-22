package EdwinDiaz20220281.Edwin_Adrian_Diaz_Henriquez_20220281.Entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity //Indicamos que esta clase es una entidad
@Table(name = "LIBROS") //Indicamos el nombre de la tabla de la entidad
@Getter @Setter @ToString @EqualsAndHashCode
public class EntityLibro {
    @Id @Column(name = "ID",insertable = false , updatable = false) //@Id sirve para indicar que es un ID en esta tabla, y en Column como columna de esta tabla, con insertable y updatable como False para indicar que este no se va a registrar o modificar
    //Anotaciones para indicar que se usa una secuencia como manera que se genera el ID, como este no es insertado  se crea el ID con una secuencia
    @SequenceGenerator(name = "seq_libro", sequenceName = "seq_libro", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_libro") //Con el nombre de la secuencia como: "seq_libro"
    private Long id;

    //En el @Column indicamos el nombre del campo, indicando que en la tabla es NOT NULL y con una longitud de 200 carácteres, ACLARACIÓN: Estas no son validaciones, son características de la tabla, pero las agrego por legibilidad
    @Column(name = "TITULO", nullable = false, length = 200)
    private String titulo;

    @Column(name = "ISBN", nullable = false, unique = true, length = 20) //Propiedad unique = true para indicar que es único cada registro en la tabla
    private String isbn;

    @Column(name = "AÑO_PUBLICACION")
    private Integer año_publicacion;

    @Column(name = "GENERO", length = 50)
    private String genero;

    @ManyToOne @JoinColumn(name = "AUTOR_ID") //Uso de @ManyToOne y @JoinColumn para llamar la FK, Si se usara @Column normal aquí daría error, es necesario usar @JoinColumn en llaves foráneas
    private EntityAutor autor_id; //El tipo de dato es la entidad (otra clase) del autor al que pertenece este libro
}
