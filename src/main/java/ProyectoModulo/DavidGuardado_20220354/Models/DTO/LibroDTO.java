package ProyectoModulo.DavidGuardado_20220354.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode
@ToString
@Getter @Setter
public class LibroDTO {
    private Long id;
    @NotBlank(message = "Titulo obligatorio")
    private String titulo;
    @NotBlank(message = "ISBN obligatorio")
    private String isbn;
    private Long año_publicacion;
    private String genero;
    private Long autor_id;
}
