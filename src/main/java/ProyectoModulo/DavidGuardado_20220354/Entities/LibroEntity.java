package ProyectoModulo.DavidGuardado_20220354.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "LIBROS")
@EqualsAndHashCode
@ToString
@Getter @Setter
public class LibroEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_libro")
    @SequenceGenerator(name = "seq_libro", sequenceName = "seq_libro", allocationSize = 1)
    private Long id;
    @Column(name = "TITULO", nullable = false, length = 200)
    private String titulo;
    @Column(name = "ISBN", nullable = false, length = 200)
    private String isbn;
    @Column(name = "AÑO_PUBLICACION")
    private Long anio;
    @Column(name = "GENERO", length = 50)
    private String genero;
    @Column(name = "ID_AUTOR")
    private Long id_autor;
}
